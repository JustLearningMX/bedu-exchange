package me.hiramchavez.Exchange.service;

import me.hiramchavez.Exchange.dto.MonedaCurrenciesDTO;
import me.hiramchavez.Exchange.model.Moneda;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MonedaService {
    public List<Moneda> listaMonedas() {
        return Arrays.stream(Moneda.values()).toList();
    }

    public List<MonedaCurrenciesDTO> getMoneda(String moneda) {
        String finalMoneda = moneda.toUpperCase();

        checarSiExisteMoneda(finalMoneda);

        return Arrays.stream(
          Moneda.values()).filter( m -> !m.name().equals(finalMoneda))
          .map(m -> new MonedaCurrenciesDTO(m.name(), m.getValor(finalMoneda, m.name())))
          .toList();
    }

    public Map<String, Double> getCurrencyExchange(String moneda, String cantidad) {
        String finalMoneda = moneda.toUpperCase();

        checarSiExisteMoneda(finalMoneda);

        try {
            double cant = Double.parseDouble(cantidad);

            if (cant < 0)
                throw new RuntimeException("Cantidad no puede ser negativa");

            Map<String, Double> exchanges = new HashMap<>();

            List<String> res = Arrays.stream(Moneda.values())
              .filter(m -> !m.name().equals(finalMoneda))
              .map(m -> {
                  exchanges.put(m.name(), Double.parseDouble(m.getValor(finalMoneda, m.name())) * cant);
                  return m.name();
              })
              .toList();

            System.out.println(res);

            return exchanges;

        } catch (NumberFormatException e) {
            throw new RuntimeException("Cantidad no es un número");
        }
    }

    private static void checarSiExisteMoneda(String finalMoneda) {
        boolean monedaExiste = Arrays.stream(Moneda.values()).anyMatch(m -> m.name().equals(finalMoneda));
        if (!monedaExiste) {
            throw new RuntimeException("Moneda no existe");
        }
    }
}
