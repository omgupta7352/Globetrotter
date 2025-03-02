package com.SpringBootMVC.demo.controller;

import com.SpringBootMVC.demo.entity.Clue;
import com.SpringBootMVC.demo.entity.FunFact;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.SpringBootMVC.demo.entity.Destination;
import com.SpringBootMVC.demo.entity.Users;
import com.SpringBootMVC.demo.service.GameService;
import com.SpringBootMVC.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String startGame(Model model) {
        Destination destination = gameService.getRandomDestination();
        List<Clue> clues = gameService.getCluesForDestination(destination.getId());
        List<FunFact> funFacts = gameService.getFunFactsForDestination(destination.getId());

        model.addAttribute("destination", destination);
        model.addAttribute("clues", clues);
        model.addAttribute("funFacts", funFacts);

        return "game";
    }

    @PostMapping("/submitAnswer")
    public String submitAnswer(@RequestParam String username,
                               @RequestParam Long destinationId,
                               @RequestParam String userAnswer,
                               Model model) {

        Optional<Users> userOptional = userService.getUserByUsername(username);
        Users user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = userService.registerUser(username); // Register a new user
        }

        //Destination destination = gameService.getRandomDestination();
        Destination destination = gameService.getDestinationById(destinationId);
        boolean isCorrect = destination.getCorrectAnswer().equalsIgnoreCase(userAnswer);

        //System.out.println(isCorrect);

        if (isCorrect) {
            userService.updateUserScore(user, 1);  // Update score
            model.addAttribute("feedback", "Correct! 🎉");
            model.addAttribute("animation", "confetti");
        } else {
            model.addAttribute("feedback", "Incorrect! 😢");
            model.addAttribute("animation", "sad-face");
        }

        model.addAttribute("totalScore", user.getScore());

        return "result";  // Result page with feedback and next option
    }


    // Challenge Friend

    @PostMapping("/challengeFriend")
    public ResponseEntity<String> challengeFriend(@RequestParam String username, Model model) {
        Optional<Users> userOptional = userService.getUserByUsername(username);
        Users user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            user = userService.registerUser(username);
        }

        // Generate a unique invite link based on the user's ID
        String inviteLink = "http://localhost:8080/game/invite?username=" + username;

        // Generate dynamic image with score
        String imageUrl = generateDynamicImage(user.getUsername(), user.getScore());

        // Invitation link that user can share
        String inviteMessage = "Hey! I'm challenging you to play this game with me! \nPlay with me: " + inviteLink + "\nSee my score: " + imageUrl;

        return ResponseEntity.ok(inviteMessage);  // Return the full invite message with the link and image
    }


//    @GetMapping("/game/invite")
//    public String inviteFriend(@RequestParam String username, Model model) {
//        Optional<Users> userOptional = userService.getUserByUsername(username);
//        if (userOptional.isEmpty()) {
//            model.addAttribute("error", "Invite link invalid or user not found.");
//            return "error";
//        }
//
//        Users user = userOptional.get();
//        model.addAttribute("inviterUsername", username);
//        model.addAttribute("inviterScore", user.getScore());
//
//        return "inviteFriend";  // Invite friend page
//    }

    @GetMapping("/game/invite")
    public String handleInvite(@RequestParam String username, Model model, RedirectAttributes redirectAttributes) {
        Optional<Users> userOptional = userService.getUserByUsername(username);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            System.out.println("User found: " + user.getUsername());

            // Add the username as a model attribute to pass it to the game page
            redirectAttributes.addFlashAttribute("username", user.getUsername());
            redirectAttributes.addFlashAttribute("score", user.getScore());

            // Redirect to the game page after handling the invite
            return "redirect:/";
        } else {
            System.out.println("User not found: " + username);
            // Handle the case where the user doesn't exist (optional)
            model.addAttribute("error", "User does not exist");
            return "error";  // Redirect to an error page if user doesn't exist
        }
    }



    private String generateDynamicImage(String username, int score) {
        // Placeholder: Use a service like Cloudinary or canvas-based image generator
        return "https://dummyimage.com/600x400/000/fff&text=" + username + "'s+Score:+ " + score;
    }



}
