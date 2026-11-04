package com.example.zadanie2.controller;

import com.example.zadanie2.entity.Employee;
import com.example.zadanie2.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Value("${employee.job-titles}")
    private List<String> jobTitles;

    @Value("${employee.full-time-types}")
    private List<String> fullTimeTypes;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getAllEmployees(Model model) {

        model.addAttribute("employees", employeeService.findAll());

        return "employees";
    }

    @GetMapping("/{id}")
    public String getEmployee(@PathVariable Integer id, Model model) {

        model.addAttribute("employee", employeeService.findById(id));

        return "employee-detail";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {

        model.addAttribute("employee", new Employee());

        model.addAttribute("jobTitles", jobTitles);

        model.addAttribute("fullTimeTypes", fullTimeTypes);

        return "employee-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {

        Employee employee = employeeService.findById(id);

        model.addAttribute("employee", employee);
        model.addAttribute("jobTitles", jobTitles);
        model.addAttribute("fullTimeTypes", fullTimeTypes);

        return "employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @Valid @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute("jobTitles", jobTitles);
            model.addAttribute("fullTimeTypes", fullTimeTypes);

            return "employee-form";
        }

        employeeService.save(employee);

        return "redirect:/employees";
    }

    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {

        employeeService.deleteById(id);

        return "redirect:/employees";
    }
}
