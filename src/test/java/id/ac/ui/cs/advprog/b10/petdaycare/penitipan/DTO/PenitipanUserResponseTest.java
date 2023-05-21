package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.DTO;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PenitipanUserResponseTest {

    @Test
    public void testFromPenitipan() {
        Penitipan penitipan = Penitipan.builder()
                .id(1)
                .hewan(new Hewan())
                .statusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN)
                .pesanPenitipan("Test message")
                .tanggalPenitipan(new Date())
                .tanggalPengambilan(new Date())
                .tanggalDiambil(new Date())
                .build();

        PenitipanUserResponse response = PenitipanUserResponse.fromPenitipan(penitipan);

        assertEquals(penitipan.getId(), response.getPenitipanId());
        assertEquals(penitipan.getHewan(), response.getHewan());
        assertEquals(penitipan.getStatusPenitipan(), response.getStatusPenitipan());
        assertEquals(penitipan.getPesanPenitipan(), response.getPesanPenitipan());
        assertEquals(penitipan.getTanggalPenitipan(), response.getTanggalPenitipan());
        assertEquals(penitipan.getTanggalPengambilan(), response.getTanggalPengambilan());
        assertEquals(penitipan.getTanggalDiambil(), response.getTanggalDiambil());
    }
}
