package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import spittr.Spittle;
import spittr.data.SpittleRepository;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private SpittleRepository mockRepository;

    @Autowired
    public SpittleController(SpittleRepository mockRepository) {
        this.mockRepository = mockRepository;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String spittles(Model model) {
        List<Spittle> spittleList = mockRepository.findSpittles(Long.MAX_VALUE,20);
        model.addAttribute("spittleList",spittleList);
        return "spittles";
    }
}

