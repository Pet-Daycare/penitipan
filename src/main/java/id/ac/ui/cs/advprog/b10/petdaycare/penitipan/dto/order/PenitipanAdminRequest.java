package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order;


import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PenitipanAdminRequest {
    private Integer userId;
    private Integer penitipanId;
    private Hewan hewan;
    private StatusPenitipan statusPenitipan;
    private String pesanPenitipan;
    private Date tanggalPenitipan;
    private Date tanggalPengambilan;
    private Date tanggalDiambil;

    public static PenitipanAdminRequest fromPenitipan(Penitipan penitipan){
        return PenitipanAdminRequest.builder()
                .userId(penitipan.getUser().getId()) // TODO : Buat hubungan ke class user di auth
                .penitipanId(penitipan.getId())
                .hewan(penitipan.getHewan())
                .tanggalPenitipan(penitipan.getTanggalPenitipan())
                .tanggalPengambilan(penitipan.getTanggalPengambilan())
                .tanggalDiambil(penitipan.getTanggalDiambil())
                .pesanPenitipan(penitipan.getPesanPenitipan())
                .statusPenitipan(penitipan.getStatusPenitipan())
                .build();
    }
}
