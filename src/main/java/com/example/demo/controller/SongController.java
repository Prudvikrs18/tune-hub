package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.entities.Song;
import com.example.demo.services.SongService;

@Controller
public class SongController {
	@Autowired
	SongService service;
	@PostMapping("/addSong")
	public String addSong(@ModelAttribute Song song) {
		boolean songStatues= service.songExits(song.getName());
		if(songStatues == false)
		{
			service.addSong(song);
			System.out.println("Song added successfully");
		}else {
			System.out.println("Song already exists");
		}
		return "adminHome";
	}
	@GetMapping("/viewSongs")
	public String viewSongs(org.springframework.ui.Model model) {
		
		List<Song>songsList=service.fetchAllSongs();
		model.addAttribute("songs",songsList);
		return "displaySongs";
	}
	
	@GetMapping("/playSongs")
	public String playSongs(org.springframework.ui.Model model) {
		boolean premiumUser=false;
		if(premiumUser==true)
		{
		List<Song>songsList=service.fetchAllSongs();
		model.addAttribute("songs",songsList);
		return "displaySongs";
		}else {
			return "makePayment";
		}
	}
	

}
