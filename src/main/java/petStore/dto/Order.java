package petStore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    public Long id;
    public Long petId;
    public int quantity;
    public Date shipDate;
    public String status;
    public boolean complete;
}