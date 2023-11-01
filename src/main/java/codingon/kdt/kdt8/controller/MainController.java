package codingon.kdt.kdt8.controller;

import codingon.kdt.kdt8.dto.UserDTO;
import codingon.kdt.kdt8.vo.UserVO;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController는 전부 다 @ResponseBody가 붙은거다.
public class MainController {
    @GetMapping("/request")
    public String main(){
        return "request";
    }
    @GetMapping("/get/response1")
    // ?key=value
    // key=value로 들어온 값을 n으로 담는다.
    //http://localhost:8080/get/response1 이렇게는 실행 안된다.
    //http://localhost:8080/get/response1?name=kang 이렇게 와야 한다.
    public String getResponse1(@RequestParam(value = "name", required = true)String n, Model model){
        model.addAttribute("name",n);
        return "response";
    }
    @GetMapping("get/response2")
    //required = false 라면 뒤에 Params 가 없어도 실행 된다.
    //http://localhost:8080/get/response2 이렇게도 실행 된다.
    public String getResponse2(@RequestParam(value = "name",required = false)String n, Model model){
        model.addAttribute("name",n);
        return "response";
    }
    @GetMapping("get/response3/{name}/{age}")
    // ~/{value}
    public  String getResponse3(@PathVariable("name") String abc,@PathVariable String age, @PathVariable(required = false) String age2, Model model){
        model.addAttribute("name",abc);
        model.addAttribute("age",age);
        return "response";
    }
    @GetMapping({"get/response4/{name}","get/response4/{name}/{age}" })
    // ~/{value}
    // 두개가 required = false로 age가 필요 없어도 실행이 되게 하려면 위에 처럼 2가지의 경우를 다 적어두면 된다.
    public  String getResponse4(@PathVariable("name") String abc,@PathVariable(required = false) String age, Model model){
        model.addAttribute("name",abc);
        model.addAttribute("age",age);
        return "response";
    }

    @PostMapping("/post/response1")
    public String postResponse1(@RequestParam String name, Model model){
        //RequestBody : 클라이언트가 전달한 json 형태의 내용을 JSON Object로 변환해준다.
        //RequestParam의 required = true 이면 빈값이여도 무조건 있어야 한다.
        model.addAttribute("name",name);
        return "response";
    }
    @PostMapping("/post/response2")
    public String postResponse2(@RequestParam(required = false) String name, Model model){
        model.addAttribute("name",name);
        return "response"; //res.render
    }

    //ResponseBody: api 쓸 때 사용하는 친구 -> template을 호출하는 게 아니라 값을 전달
    @PostMapping("/post/response3")
    @ResponseBody
    public String postResponse3(@RequestParam(required = false) String name, Model model){
        model.addAttribute("name",name);
        return "response"; //res.send(res.json)
    }

    @GetMapping("/dto/response1")
    @ResponseBody
    public String dtoAPI1(@ModelAttribute UserDTO userDTO){
        //ModelAttribute: html 폼 데이터를 컨트롤러로 전달할 때 사용. get이나 post 전송에서 사용
        String msg = userDTO.getName() +" "+ userDTO.getAge()+"!!!!";
        return msg;
    }
    //일반 폼 Get 방식 - DTO(@ModelAttribute) 전송 가능
    //일반 폼 Get 방식 - DTO(@RequestBody) 오류 발생

    @PostMapping("/dto/response2")
    @ResponseBody
    public String dtoAPI2(UserDTO userDTO){
        String msg = userDTO.getName() + " " + userDTO.getAge() + "!!!";
        return msg;
    }
    @PostMapping("/dto/response3")
    @ResponseBody
    public String dtoAPI3( @RequestBody UserDTO userDTO){
        //RequestBody : json 또는 xml 데이터 형식을 읽는다.(axios)
        //일반 폼 전송 시 데이터 형식은 기본값으로 x-www-form-urlencoded
        String msg = userDTO.getName() + " " + userDTO.getAge() + "!!!";
        return msg;
    }

    @GetMapping("/vo/response1")
    @ResponseBody
    public String voAPI1(UserVO userVO){
        String msg = userVO.getName() + " " + userVO.getAge() + "!!!";
        return msg;
    }

    @GetMapping("/axios/response1")
    @ResponseBody
    public String axiosResponse1(@RequestParam String name, @RequestParam String age){
        String msg = "이름: " + name + ", 나이: " + age;
        return msg;
    }

    @GetMapping("/axios/response2")
    @ResponseBody
    public String axiosResponse2(UserDTO userDTO){
        String msg = "이름: " + userDTO.getName() + ", 나이: "+ userDTO.getAge();
        return msg;
    }

    @PostMapping("/axios/response3")
    @ResponseBody
    //@RequstParam required 기본값이 true
    //axios로 값을 전달하게 될 경우 파라미터로 값이 들어오지 않는다.(Post로 보냈을 때)
    //값이 들어오지 않는데, @RequestParasm의 required가 기본값이 true이기 때문에 오류
    public String axiosResponse3(@RequestParam String name, @RequestParam String age){
        String msg = "이름: " + name + ", 나이: "+ age;
        return  msg;

    }

    @PostMapping("/axios/response4")
    @ResponseBody
    public String axiosResponse4(UserDTO userDTO){
        String msg = "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
        return msg;
    }

    @PostMapping("/axios/response5")
    @ResponseBody
    public String axiosResponse5(@RequestBody UserDTO userDTO){
        String msg = "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
        return msg;
    }
    //@RequestParam @ModelAttribute : 매개변수(파라미터)로 전달된 친구들을 변환
    //@RequestBody: json 형태의 데이터 (데이터)

    @GetMapping("/axios/vo/response2")
    @ResponseBody
    public String axiosVoResponse2(UserVO userVO){
       //@ModelAttribute로 값이 들어갈 때는 setter 함수를 실행해서 값을 넣어주기 때문에
       //setter 함수가 없는 UserVO에는 값이 들어갈 수 없다.
        String msg = "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
        return msg;
    }

    @PostMapping("/axios/vo/response4")
    @ResponseBody
    public String axiosVoResponse4(UserVO userVO){
        String msg = "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
        return msg;
    }

    @PostMapping("/axios/vo/response5")
    @ResponseBody
    public String axiosVoResponse5(@RequestBody UserVO userVO){
        //@RequestBody로 값을 전달할때 userVO에 setter 함수가 없어도 값이 들어간다.
        //@RequestBody는 setter 함수 실행이 아니라 각각의 필드(변수)에 직접적으로 값을 주입하면서 매핑
        //@ModelAttribute가 setter 함수를 실행해 값을 넣어준다면
        //@RequestBody는 각각의 필드(변수)에 직접적으로 값을 주입한다. 필드에 내장된 set 함수를 실행
        String msg = "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
        return msg;
    }
}
