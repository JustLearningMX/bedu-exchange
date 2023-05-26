package me.hiramchavez.Exchange.controller;

import me.hiramchavez.Exchange.dto.MonedaCurrenciesDTO;
import me.hiramchavez.Exchange.model.Moneda;
import me.hiramchavez.Exchange.service.MonedaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/currencies")
public class MonedaController {

    private final MonedaService monedaService;

    @Autowired
    public MonedaController(MonedaService monedaService) {
        this.monedaService = monedaService;
    }

    @GetMapping
    public ResponseEntity<List<Moneda>> listaMonedas() {
        List<Moneda> monedas = monedaService.listaMonedas();

        return ResponseEntity.ok().body(monedas);
    }

    @GetMapping("/{moneda}")
    public ResponseEntity<List<MonedaCurrenciesDTO>> getMoneda(@PathVariable String moneda) {
        List<MonedaCurrenciesDTO> currencies = monedaService.getMoneda(moneda);

        return ResponseEntity.ok().body(currencies);
    }

    @GetMapping("/{moneda}/exchange/{cantidad}")
    public ResponseEntity<Map<String, Double>> getCurrencyExchange(
      @PathVariable("moneda") String moneda,
      @PathVariable("cantidad") String cantidad
    ) {
        Map<String, Double> exchanges = monedaService.getCurrencyExchange(moneda, cantidad);
        return ResponseEntity.ok().body(exchanges);
    }
}
