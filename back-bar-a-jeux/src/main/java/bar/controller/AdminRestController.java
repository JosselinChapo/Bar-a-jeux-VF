package bar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import bar.model.Admin;
import bar.model.Views;
import bar.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

	@Autowired
	private AdminService adminService;

	@GetMapping("")
	@JsonView(Views.ViewAdmin.class)
	public List<Admin> findAll() {
		List<Admin> admins = adminService.findAll();

		return admins;
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewAdmin.class)
	public Admin findById(@PathVariable Integer id) {
		Admin admin = adminService.findById(id);

		return admin;
	}

	@PostMapping("")
	@JsonView(Views.ViewAdmin.class)
	public Admin save(@RequestBody Admin admin) {
		admin = adminService.save(admin);
		
		return admin;
	}
	
	@PutMapping("/{id}")
	@JsonView(Views.ViewAdmin.class)
	public Admin update(@RequestBody Admin admin, @PathVariable Integer id) {

		admin = adminService.update(admin);
		
		return admin;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		adminService.delete(id);
	}

}
