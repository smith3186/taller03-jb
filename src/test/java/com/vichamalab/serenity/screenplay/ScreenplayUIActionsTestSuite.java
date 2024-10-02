package com.vichamalab.serenity.screenplay;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.vichamalab.serenity.screenplay.rest.RestApiActions;

import net.serenitybdd.junit5.SerenityJUnit5Extension;

@ExtendWith(SerenityJUnit5Extension.class)
public class ScreenplayUIActionsTestSuite {
	RestApiActions restApiActions;
	
	@Test
	@Tag("uiactions")
	public void crearProducto() {
		restApiActions.dadoUnaApiConUrl();
		restApiActions.cuandoSeEnviaPeticionConNombreDescripcionYPrecio(0l);
		restApiActions.entoncesRecibeRespuestaExitosayMensaje();
	}
}
