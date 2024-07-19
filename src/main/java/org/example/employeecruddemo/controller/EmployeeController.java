package org.example.employeecruddemo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.employeecruddemo.entity.Employee;
import org.example.employeecruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/* 1.
    form obj created
    data needed populate
    render from page
   2.
    Data Conversion , Validation
    Database.save
    redirect

 */


@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    // '/update-employee?id=
    @GetMapping("/update-employee")
    public String updateEmployee(@RequestParam("id")int id, Model model) {
        model.addAttribute("employee",
                employeeService.findEmployeeBindId(id));
        employeeId = id;
        model.addAttribute("employeeId",id);
        return "employeeForm";
    }

    int employeeId ;



    @PostMapping("/update-employee")
    public String processUpdateEmployee(@Valid Employee employee, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "employeeForm";
        }
        employeeService.updateEmployee(employeeId, employee);
        redirectAttributes.addFlashAttribute("update", "Employee updated successfully");
        return "redirect:/employees";
    }





    @GetMapping("/")
    public String index() {
        return "redirect:/employees";
    }

    @GetMapping("/employees")
    public String showAllEmployees(Model model) {
        model.addAttribute("employeesList", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping(value = "/add_employee")
    public String addEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        model.addAttribute("employeeId", 0);
        return "employeeForm";
    }

    @PostMapping(value = "/do_add_employee")
    public String saveEmployee(@Valid Employee employee, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "employee";
        }else {
            employeeService.insertEmployee(employee);
            redirectAttributes.addFlashAttribute("success", "Added Employee Successfully");
            return "redirect:/employees";
        }
    }

    @GetMapping("/delete-employee")
    public String deleteEmployee(@RequestParam("id") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
