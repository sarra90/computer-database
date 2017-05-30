package com.excilys.controller;


    import java.util.Optional;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Controller;
    import org.springframework.ui.Model;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RequestMethod;
    import org.springframework.web.bind.annotation.RequestParam;

    import com.excilys.dto.ComputerDto;
    import com.excilys.mapper.MapperComputer;
    import com.excilys.model.Computer;
    import com.excilys.model.ValidatorModel;
    import com.excilys.service.impl.CompanyServiceImpl;
    import com.excilys.service.impl.ComputerServiceImpl;
    import com.excilys.validations.DateValidator;

    @Controller
    public class EditComputerController {

        @Autowired
        private ComputerServiceImpl computerService;
        @Autowired
        private CompanyServiceImpl companyService;

        @RequestMapping(value = "/editComputer", method = RequestMethod.GET)
        public String editComputer(@RequestParam(value = "id") String id, Model model) {

            model.addAttribute("list", companyService.findAll());
            if (isNumber(id)) {
                long idComputer = Long.valueOf(id);
                Computer computer = computerService.findById(idComputer);
                model.addAttribute("idComputer", idComputer);
                
                    model.addAttribute("name", computer.getName());
                    model.addAttribute("introduced", computer.getIntroduced());
                    model.addAttribute("discontinued", computer.getDisconstinued());
                    if (computer.getCompany() != null) {
                        model.addAttribute("nameCompany", computer.getCompany().getName());
                    }

                
            }
            return "editComputer";
        }

        @RequestMapping(value = "/editComputer", method = RequestMethod.POST)
        public String addComputer(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name,
                @RequestParam(value = "introduced") String introduced,
                @RequestParam(value = "discontinued") String discontinued,
                @RequestParam(value = "companyId") String companyId, Model model) {

            Long idCompany = Long.valueOf(companyId).longValue();

            return "redirect:/editcomputer";
        }

        private boolean isNumber(String s) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            } catch (NullPointerException e) {
                return false;
            }
            return true;
        }
    
}
