package com.adriannavarrogabino.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.adriannavarrogabino.models.entity.Usuario;
import com.adriannavarrogabino.models.services.IUsuarioService;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

	@Autowired
	private IUsuarioService usuarioService;

	@Override
	public OAuth2AccessToken enhance(
			OAuth2AccessToken accessToken,
			OAuth2Authentication authentication) {
		
		Usuario usuario = usuarioService.findByUsername(
				authentication.getName());

		Map<String, Object> info = new HashMap<String, Object>();
		
		info.put("id", usuario.getId());
		info.put("ultimoAcceso", usuario.getUltimoAcceso().toString());
		info.put("fechaNacimiento", usuario.getFechaNacimiento().toString());
		info.put("nombre", usuario.getNombre());
		info.put("apellidos", usuario.getApellidos());
		info.put("correo", usuario.getCorreo());
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
		
		return accessToken;
	}
}
