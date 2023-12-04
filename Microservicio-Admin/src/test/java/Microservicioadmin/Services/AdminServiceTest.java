package Microservicioadmin.Services;

import Microservicioadmin.Dto.DtoCuenta;
import Microservicioadmin.Dto.DtoMonopatin;
import Microservicioadmin.Dto.DtoParada;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AdminService adminService;


    @Test
    void saveMonopatin() { //verifica que devuelva  un dto monopatin tras haber recibido como parametro un dtoMonopatin
        when(restTemplate.postForObject(anyString(), any(), eq(DtoMonopatin.class))).thenReturn(new DtoMonopatin());
        adminService.saveMonopatin(new DtoMonopatin());
        verify(restTemplate, times(1)).postForObject(anyString(), any(), eq(DtoMonopatin.class));
    }

    @Test
    void saveParada() {
        when(restTemplate.postForObject(anyString(), any(), eq(DtoParada.class))).thenReturn(new DtoParada());
        adminService.saveParada(new DtoParada());
        verify(restTemplate, times(1)).postForObject(anyString(), any(), eq(DtoParada.class));
    }

    @Test
    void saveCuenta() {
        when(restTemplate.postForObject(anyString(), any(), eq(DtoCuenta.class))).thenReturn(new DtoCuenta());
        adminService.saveCuenta(new DtoCuenta());
        verify(restTemplate, times(1)).postForObject(anyString(), any(), eq(DtoCuenta.class));
    }

    @Test
    void deleteMonopatin() { //ya que devuelven void la principal funcionalidad es ver si se llama bien al metodo
        adminService.deleteMonopatin(123);
        verify(restTemplate, times(1)).delete("http://localhost:8081/monopatin/123");
    }

    @Test
    void deleteParada() {
        adminService.deleteParada(123);
        verify(restTemplate, times(1)).delete("http://localhost:8081/api/parada/123");
    }

    @Test
    void deleteCuenta() {
        adminService.deleteCuenta(123);
        verify(restTemplate, times(1)).delete("http://localhost:8080/api/cuenta/123");
    }

    @Test
    void updatePrecio() {
        double precio = 100.0;
        String fecha = "2023-01-01";
        adminService.updatePrecio(precio, fecha);
        String expectedUrl = "http://localhost:8082/api/viaje/actualizar?precio=100.0&fecha=2023-01-01";
        verify(restTemplate, times(1)).put(eq(expectedUrl), isNull());
    }

    @Test
    void getRecaudacion() {
        double recaudacionEsperada = 1000.0;
        ResponseEntity<Double> responseEntity = new ResponseEntity<>(recaudacionEsperada, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(Double.class))).thenReturn(responseEntity);
        int anio = 2023;
        int mesIni = 1;
        int mesFin = 12;
        double recaudacion = adminService.getRecaudacion(anio, mesIni, mesFin);
        verify(restTemplate, times(1)).getForEntity(
                eq("http://localhost:8082/api/viaje/recaudacion?anio=" + anio + "&mesIni=" + mesIni + "&mesFin=" + mesFin),
                eq(Double.class)
        );
        assertEquals(recaudacionEsperada, recaudacion, 0.001);

    }

    @Test
    void getCantMonopatinDisponible() {
        ResponseEntity<Integer> mockResponse = new ResponseEntity<>(10, HttpStatus.OK);
        when(restTemplate.getForEntity(eq("http://localhost:8081/monopatin/disponibles"), eq(Integer.class)))
                .thenReturn(mockResponse);
        int result = adminService.getCantMonopatinDisponible();
        verify(restTemplate, times(1)).getForEntity(eq("http://localhost:8081/monopatin/disponibles"), eq(Integer.class));
        assertEquals(10, result);
    }

    @Test
    void getCantMonopatinMantenimiento() {
        ResponseEntity<Integer> mockResponse = new ResponseEntity<>(5, HttpStatus.OK);
        when(restTemplate.getForEntity(eq("http://localhost:8081/monopatin/mantenimiento"), eq(Integer.class)))
                .thenReturn(mockResponse);
        int result = adminService.getCantMonopatinMantenimiento();
        verify(restTemplate, times(1)).getForEntity(eq("http://localhost:8081/monopatin/mantenimiento"), eq(Integer.class));
        assertEquals(5, result);
    }

    @Test
    void getAllMonopatinesByYear() throws Exception{
        List<DtoMonopatin> monopatines = Arrays.asList(new DtoMonopatin(), new DtoMonopatin());
        ResponseEntity<List> responseEntity = new ResponseEntity<>(monopatines, HttpStatus.OK);
        when(restTemplate.getForEntity(anyString(), eq(List.class)))
                .thenReturn(responseEntity);
        List<DtoMonopatin> resultado = adminService.getAllMonopatinesByYear(2023, 2);
        verify(restTemplate, times(1)).getForEntity(anyString(), eq(List.class));
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
    }
}