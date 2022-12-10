package com.example.testprac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class MyCOntroller {


    @Autowired
    private StuRepository stuRepository;

//    @Autowired
//    private ServiceImp serviceImp;

    @GetMapping("/hello")
    public String hello()
    {
        return "hello";
    }

    @GetMapping(path = "/index")
    public String Fun(Model model)
    {
        List<Users> usersList =stuRepository.findAll();
        model.addAttribute("listStudents", usersList);
        List<String> nameList =usersList.stream().map(x->x.getName()).collect(Collectors.toList());
        model.addAttribute("liStudents", nameList);

        return "home";
    }

    @GetMapping("/delete")
    public String del(Long id)
    {
        stuRepository.deleteById(id);
        return "redirect:/";
    }


    @GetMapping("/edit")
    public String Edi(Long id, Model model)
    {
        Users student = stuRepository.findById(id).orElse(null);
        if(student==null) throw new RuntimeException("Patient does not exist");
        model.addAttribute("student", student);

        return "editS";

    }

    @PostMapping("/save")
    public String register(@ModelAttribute Users u)
    {
        System.out.println(u);
        stuRepository.save(u);
        stuRepository.findAll();
        return "redirect:/index";

    }

    @PostMapping("/register")
    public String regist(@ModelAttribute Users users)
    {

//        serviceImp.addUser(users);
        stuRepository.save(users);
        stuRepository.findAll();
        return "home";

    }



    }





