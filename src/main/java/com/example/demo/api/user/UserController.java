package com.example.demo.api.user;

import com.example.demo.dtoes.CommentDto;

import com.example.demo.entities.Post;
import com.example.demo.entities.User;
import com.example.demo.service.PostInterface;
import com.example.demo.service.UserInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserInterface userInterface;
    private final PostInterface postInterface;
    @GetMapping("/update/user/{idCurrentUser}")
    public String update(@PathVariable Long idCurrentUser, Model model,Model model2){
        User user  = userInterface.getCurrentUser(idCurrentUser);
        model.addAttribute("currentUser",user);
        model2.addAttribute("ci",idCurrentUser);
        System.out.println(idCurrentUser);
        return "updateUser-page";
    }
    @PostMapping("/update2/{idCurrentUser}")
    public String update2(@PathVariable Long idCurrentUser, @ModelAttribute("currentUser") User user) {
        userInterface.updateUser(user);
        System.out.println(idCurrentUser);
        return "redirect:/myProfile/" + idCurrentUser;
    }
    @GetMapping("/subscribe/{userId}/{currentUserId}")
    public String subscribe(@PathVariable Long userId,@PathVariable Long currentUserId){
        userInterface.subscribe(userId,currentUserId);
        return "redirect:/searchUser";
    }
    @GetMapping("/profile/{userId}")
    public String profile(@PathVariable Long userId,Model model,Model postModel){
        User user = userInterface.profile(userId);
        List<Post> posts = userInterface.getAllPostFindUser(userId);
        postModel.addAttribute("findUserPosts",posts);
        model.addAttribute("profileUser",user);
        return "viewProfile";
    }
    @GetMapping("/subscribers/{userId}")
    public String subscribers(@PathVariable Long userId,Model model){
        List<User> users = userInterface.findUserGetAllSubscribers(userId);
        model.addAttribute("allSubscribers",users);
        return "subscribers-page";
    }
    @GetMapping("/subscriptions/{userId}")
    public String subscriptions(@PathVariable Long userId,Model model){
        List<User> users = userInterface.findUserGetAllSubscriptions(userId);
        model.addAttribute("allSubscriptions",users);
        return "subscriptions-page";
    }
    @GetMapping("/like/{id}/{currentUserId}")
    public String like(@PathVariable Long id,@PathVariable Long currentUserId) {
        postInterface.likePost(id,currentUserId);
        return "redirect:/search";
    }
    @GetMapping("/info/{id}/{currenUserId}")
    public String info(@PathVariable Long id,@PathVariable Long currenUserId,Model model,Model model2) {
        List<CommentDto> commentDTO = postInterface.infoAboutCurrentPost(id);
        System.out.println("EEEEEEEEEEEEEEEEEEEE"+ commentDTO.get(0).getComment());
        model.addAttribute("infoPost",commentDTO);
        model2.addAttribute("currentUserId",currenUserId);
        return "infoComment-page";
    }

    @PostMapping("/comment/{postId}/{currentUserId}")
    public String addComment(@PathVariable Long postId,@PathVariable Long currentUserId,@RequestParam String comment) {
        postInterface.addComment(postId,currentUserId,comment);
        return "redirect:/search";
    }
    @PostMapping("/likeComment/{commentId}/{currentUserId}")
    public String likeComment(@PathVariable Long commentId,@PathVariable Long currentUserId) {
        System.out.printf("+++++++++++" +  commentId);
        postInterface.addLikeToComment(commentId,currentUserId);
        System.out.println("!!!!!!!!!!!!!!!!!");
        return "redirect:/search/" + currentUserId;
    }


}
