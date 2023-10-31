package codingon.kdt.kdt8.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;

@Controller

public class HelloController {
    @GetMapping("/")
    public String getHello(Model model){
        model.addAttribute("msg","<strong>Hello</strong>");
        model.addAttribute("name","kim");
        ArrayList<String> coffee = new ArrayList<>();
        coffee.add("americano");
        coffee.add("americano");
        coffee.add("americano");
        coffee.add("americano");
        model.addAttribute("coffee", coffee);

        model.addAttribute("age","10");
        return "hello";
    }

    //컨트롤러에서 리턴에 글자를 입력하면 파일명을 찾게 된다.
    //즉, getmapping으로 URL 주소가 들어왔을 때 return에 있는 글자를 templates에 있는 파일에서 찾아서 불러온다.
    //읽어오는 우선순위: templates => static 순으로 읽어온다. (return된 글자명의 파일을 찾는 순서)
    @GetMapping("/hello")
    public String getHello2(){
        return "hello";
    }

    @GetMapping("/people")
    public  String People(Model model){

        // ArrayList > String[]
        ArrayList<String[]> people = new ArrayList<String[]>();

        people.add(new String[]{"kim", "10"});
        people.add(new String[]{"lee","20"});
        people.add(new String[]{"hong","30"});
        people.add(new String[]{"park","40"});
        people.add(new String[]{"shin","50"});

        model.addAttribute("people",people);

        return "poeple";
    }

}
