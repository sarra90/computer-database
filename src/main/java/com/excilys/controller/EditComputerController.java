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
            Optional<Computer> computer = computerService.findById(idComputer);
            model.addAttribute("idComputer", idComputer);
            if (computer.isPresent()) {
                model.addAttribute("name", computer.get().getName());
                model.addAttribute("introduced", computer.get().getIntroduced());
                model.addAttribute("discontinued", computer.get().getDisconstinued());
                if (computer.get().getManufacturer() != null) {
                    model.addAttribute("nameCompany", computer.get().getManufacturer().getName());
                }

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

        ValidatorModel introducedValidator = DateValidator.isValidDate(introduced);
        ValidatorModel disconstinuedValidator = DateValidator.isValidDate(discontinued);
        if (isNumber(id)) {
            long idcomputer = Long.valueOf(id).longValue();
            if (introducedValidator.isValid() && disconstinuedValidator.isValid()) {
                ValidatorModel orderDateValidator = DateValidator.isIntruducedDateBeforeDisconstinuedDate(introduced,
                        discontinued);
                if (orderDateValidator.isValid()) {
                    ComputerDto computerDto = new ComputerDto.Builder().id(idcomputer).name(name).introduced(introduced)
                            .disconstinued(discontinued).idCompany(idCompany).build();

                    Computer computer = MapperComputer.convertToComputer(computerDto);

                    computerService.update(computer);

                } else {
                    // display errors
                    model.addAttribute("erreur", orderDateValidator.getError());
                }
            } else {
                model.addAttribute("erreurIntroduced", introducedValidator.getError());
                model.addAttribute("erreurDiscontinued", disconstinuedValidator.getError());
            }
        }
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
