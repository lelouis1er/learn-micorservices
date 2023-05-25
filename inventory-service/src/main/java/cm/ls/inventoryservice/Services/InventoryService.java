package cm.ls.inventoryservice.Services;

import cm.ls.inventoryservice.Model.Inventory;
import cm.ls.inventoryservice.dto.InventoryResponse;
import cm.ls.inventoryservice.repository.InventoryRepository;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {

        return skuCode.stream()
                        .map(this::mapSkuToInventoryResponse)
                .collect(Collectors.toList());

        /*List<Inventory> temp = inventoryRepository.findBySkuCodeIn(skuCode);
        System.out.println("taille:" + temp.size());
        temp.forEach(a ->{
            System.out.println("r:" + a.getSkuCode());
        });
        return temp.stream()
                .map(this::mapInventoryToInventoryResponse)
                .collect(Collectors.toList());*/
    }

    private InventoryResponse mapSkuToInventoryResponse(String skuCode) {
        Optional<Inventory> op = inventoryRepository.findInventoriesBySkuCode(skuCode);
        if (! op.isPresent()) {
            return new InventoryResponse(skuCode, false);
        } else {
            return InventoryResponse.builder()
                    .skuCode(skuCode)
                    .isInStock(op.get().getQte() > 0)
                    .build();
        }
    }
    private InventoryResponse mapInventoryToInventoryResponse(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQte() > 0)
                .build();
    }

}
