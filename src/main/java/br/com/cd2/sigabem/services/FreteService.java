package br.com.cd2.sigabem.services;

import br.com.cd2.sigabem.dto.FreteDto;
import br.com.cd2.sigabem.models.Frete;
import br.com.cd2.sigabem.repository.FreteRepository;
import br.com.cd2.sigabem.response.Cep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FreteService {

    @Autowired
    private FreteRepository repository;

    RestTemplate restTemplate = new RestTemplate();


    public Frete create(FreteDto freteDto){
        Frete frete = new Frete();
        frete.setCepDestino(freteDto.getCepDestino());
        frete.setCepOrigem(freteDto.getCepOrigem());
        frete.setNomeDestinatario(freteDto.getNomeDestinatario());
        frete.setPeso(freteDto.getPeso());
        frete.setVlTotalFrete(calcValorFrete(freteDto));

        frete.setDataConsulta(LocalDateTime.now());
        frete.setDataPrevistaEntrega(calcDataPrevista(freteDto));

        repository.save(frete);

        return frete;
    }

    public List<Frete> findAll() {
        return repository.findAll();
    }

    public Frete findById(Long id) {
        Optional<Frete> obj = repository.findById(id);
        obj.get().setDataConsulta(LocalDateTime.now());
        repository.save(obj.get());
        return obj.orElseThrow(RuntimeException::new);
    }


    public BigDecimal calcValorFrete(FreteDto freteDto){
        Double taxPerKg = 1.0;
        Cep cepOrigem = getInfoCep(freteDto.getCepOrigem());
        Cep cepDestino = getInfoCep(freteDto.getCepDestino());

        double vlTotalFrete = calcTax(freteDto.getPeso()) * calcDesconto(cepOrigem, cepDestino);

        return BigDecimal.valueOf(vlTotalFrete);
    }

    public Double calcTax(Double peso){
        Double taxPerKg = 1.0;
        return peso * taxPerKg;
    }

    public Double calcDesconto(Cep cepOrigem, Cep cepDestino){

        if(Objects.equals(cepOrigem.getDdd(), cepDestino.getDdd())){
            return  0.5;
        }

        if(Objects.equals(cepOrigem.getUf(), cepDestino.getUf())){
            return 0.25;
        }

        return 1.0;
    }

    public LocalDateTime calcDataPrevista(FreteDto freteDto){
        Cep cepOrigem = getInfoCep(freteDto.getCepOrigem());
        Cep cepDestino = getInfoCep(freteDto.getCepDestino());

        LocalDateTime data = LocalDateTime.now();
        if(Objects.equals(cepOrigem.getDdd(), cepDestino.getDdd())){

            return data.plusDays(1);
        }

        if(Objects.equals(cepOrigem.getUf(), cepDestino.getUf())){
            return data.plusDays(3);
        }

        return data.plusDays(10);
    }

    public Cep getInfoCep(String cep){
        return restTemplate.getForObject("https://viacep.com.br/ws/"+cep+"/json/", Cep.class);
    }
}