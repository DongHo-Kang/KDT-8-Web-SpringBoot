package codingon.kdt.kdt8.controller;

import codingon.kdt.kdt8.vo.PracticeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PracticeController {
    @GetMapping("/practice")
    public String main(){
        return "practice_form_request_231031";
    }
    @GetMapping("/introduce/{name}")
    public String introduce(@PathVariable String name, Model model){
        model.addAttribute("name", name);
        return "practice";
    }
    @GetMapping("/introduce2")
    public String introduce2(@RequestParam String name, @RequestParam String age, Model model){
        model.addAttribute("name", name);
        model.addAttribute("age",age);
        return  "practice";
    }

    @PostMapping("/post/signup")
    public  String signup(@RequestParam String name, @RequestParam String gender, @RequestParam String year,@RequestParam String month,@RequestParam String day,@RequestParam String hobby, Model model){
        model.addAttribute("name",name);
        model.addAttribute("gender", gender);
        model.addAttribute("year", year);
        model.addAttribute("month",month);
        model.addAttribute("day", day);
        model.addAttribute("hobby", hobby);
        return "practice_form_post_231031";
    }

    @GetMapping("/axios_practice")
    public String axios(){
        return "practice_form_axios_231101";
    }

    @PostMapping("/axios/signup1")
    @ResponseBody
    public String axios(@RequestBody PracticeVO practiceVO){
        String msg = practiceVO.getName() + "회원가입 성공";
        return msg;
    }
}
