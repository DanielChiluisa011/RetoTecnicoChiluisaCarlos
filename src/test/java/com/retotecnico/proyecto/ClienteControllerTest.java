package com.retotecnico.proyecto;

import com.retotecnico.proyecto.application.command.create.CreateClienteCommand;
import com.retotecnico.proyecto.application.dto.response.CreateClienteResponse;

import io.github.jkratz55.mediator.core.Mediator;

import com.retotecnico.proyecto.api.controller.ClientesController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ClienteControllerTest {

    @InjectMocks
    private ClientesController clienteController;

    @Mock
    private Mediator mediator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPostCliente_Exito() {
        CreateClienteCommand command = new CreateClienteCommand();
        command.setNombre("Juan Pérez");
        command.setDirección("Calle 123");
        command.setTelefono("123456789");
        command.setEdad(30);
        command.setGenero("M");
        command.setIdentificacion("987654321");
        command.setContrasena("secreta");
        command.setEstado(true);

        CreateClienteResponse fakeResponse = new CreateClienteResponse("Cliente creado con éxito");
        when(mediator.dispatch(any(CreateClienteCommand.class))).thenReturn(fakeResponse);
        ResponseEntity<CreateClienteResponse> response = clienteController.postCliente(command);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void testPostCliente_ErrorInterno() {
        CreateClienteCommand command = new CreateClienteCommand();
        command.setNombre("Juan Pérez");
        when(mediator.dispatch(any(CreateClienteCommand.class))).thenThrow(new RuntimeException("Error"));
        ResponseEntity<CreateClienteResponse> response = clienteController.postCliente(command);
        assertNotNull(response);
        assertEquals(500, response.getStatusCodeValue());
    }
}
