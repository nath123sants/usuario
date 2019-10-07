package com.usuario.usuario.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.usuario.usuario.models.Usuario;
import com.usuario.usuario.repository.UsuarioRepository;

@Controller
public class UsuarioController {
	@Autowired
	private UsuarioRepository ur;

	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView("formUsuario");
		Iterable<Usuario> usuarios = ur.findAll();
		mv.addObject("usuarios", usuarios);
		return mv;
	}

	@RequestMapping("/deletar")
	public String deletarUsuario(int id) {
		Usuario usuario = ur.findById(id);
		ur.delete(usuario);
		return "redirect:/cadastrarUsuario";
	}

	@RequestMapping(value = "/cadastrarUsuario", method = RequestMethod.POST)
	public String form(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/cadastrarUsuario";
		}
		ur.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuario salvo com sucesso!");
		return "redirect:/cadastrarUsuario";
	}
}
