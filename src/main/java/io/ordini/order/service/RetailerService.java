package io.ordini.order.service;

import io.ordini.order.controller.exception.OrderException;
import io.ordini.order.domain.model.RetailerModel;
import io.ordini.order.repositories.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RetailerService {

    @Autowired
    private RetailerRepository retailerRepository;

    public List<RetailerModel> getRetailer() {
        return retailerRepository.findAll();
    }

    public RetailerModel getRetailerById(UUID id) {
        return retailerRepository.findById(id)
                .orElseThrow(() -> new OrderException("Revendedor não encontrado.", HttpStatus.NOT_FOUND));
    }

    public RetailerModel createRetailer(RetailerModel retailer) {
        return retailerRepository.save(retailer);
    }

    public RetailerModel updateRetailer(UUID id,RetailerModel retailer) {
        RetailerModel retailerResult = retailerRepository.getReferenceById(id);
        retailerResult.setRetailer_name(retailer.getRetailer_name());
        retailerResult.setLat(retailer.getLat());
        retailerResult.setLon(retailer.getLon());

        return retailerRepository.save(retailerResult);

    }

    public void deleteRetailer(UUID id) {
        retailerRepository.deleteById(id);
    }

}
