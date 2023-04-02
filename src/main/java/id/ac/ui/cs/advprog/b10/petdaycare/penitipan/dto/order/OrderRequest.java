package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private List<OrderDetailsData> orderDetailsData;
}
