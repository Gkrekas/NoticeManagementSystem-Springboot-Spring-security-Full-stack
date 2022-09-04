package com.example.notice.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.notice.entity.Notes;
import com.example.notice.entity.UserDtls;
import com.example.notice.repository.NotesRepository;
import com.example.notice.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private NotesRepository notesRepo; 
	
	@Autowired
	private UserRepository userRepo;
	
	@ModelAttribute
	public void addCommonData(Principal p, Model m) {
		String email=p.getName();
		UserDtls user =userRepo.findByEmail(email);
		m.addAttribute("user", user);
	}
	
	@GetMapping("/viewNotes/{page}")
	public String viewNotes(@PathVariable int page, Model  m, Principal p) {
		String email=p.getName();
		UserDtls user = userRepo.findByEmail(email);
		Pageable pageable = PageRequest.of(page, 5, Sort.by("id").descending());
		Page<Notes> notes = notesRepo.findNotesByUser(user.getId(), pageable);
		m.addAttribute("pageNo", page);
		m.addAttribute("totalPage", notes.getTotalPages());
		m.addAttribute("Notes", notes);
		m.addAttribute("totalElement", notes.getTotalElements());

		return "user/view_notes";
	}
	
	@GetMapping("/addNotes")
	public String addNotes() {  
		return "user/add_notes";
	}
	
	@PostMapping("/saveNotes")
	public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal p) {
		String email = p.getName();
		UserDtls u = userRepo.findByEmail(email);
		notes.setUser(u);

		Notes n = notesRepo.save(notes);

		if (n != null) {
			session.setAttribute("msg", "Notes Added Sucessfully");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}

		return "redirect:/user/addNotes";
	}
	
	
	@GetMapping("/editNotes/{id}")
	public String editNotes(@PathVariable int id, Model m) {
		Optional<Notes> n= notesRepo.findById(id);
		if(n.isPresent()) {
			Notes notes = n.get();
			m.addAttribute("notes", notes);
		}
		return "user/edit_notes";
	}
	
	@PostMapping("/updateNotes")
	public String editNotes(@ModelAttribute Notes notes, HttpSession session, Principal p ) {
		String email=p.getName();
		UserDtls user = userRepo.findByEmail(email);
		notes.setUser(user);
		Notes updateNotes  = notesRepo.save(notes);
		if (updateNotes != null) {
			session.setAttribute("msg", "Notes Update Sucessfully");
		} else {
			session.setAttribute("msg", "Something wrong on server");
		}
		return "redirect:/user/viewNotes/0";
	}
	
	@GetMapping("/deleteNotes/{id}")
	public String deleteNotes(@PathVariable int id, HttpSession session) {
		Optional<Notes> n = notesRepo.findById(id);
		if(n.isPresent()) {
			notesRepo.deleteById(id);
			session.setAttribute("msg", "Delete OK");
		}
		return "redirect:/user/viewNotes/0";
	}

}
