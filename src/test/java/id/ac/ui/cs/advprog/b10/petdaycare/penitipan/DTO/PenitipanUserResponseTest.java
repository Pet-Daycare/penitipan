package id.ac.ui.cs.advprog.b10.petdaycare.penitipan.DTO;

import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.dto.order.PenitipanUserResponse;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.hewan.Hewan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.Penitipan;
import id.ac.ui.cs.advprog.b10.petdaycare.penitipan.model.order.StatusPenitipan;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PenitipanUserResponseTest {

    @Test
    void testFromPenitipan() {
        Penitipan penitipan = Penitipan.builder()
                .id(1)
                .hewan(new Hewan())
                .statusPenitipan(StatusPenitipan.UNVERIFIED_PENITIPAN)
                .pesanPenitipan("Test message")
                .tanggalPenitipan(LocalDateTime.now())
                .tanggalPengambilan(LocalDateTime.now())
                .tanggalDiambil(LocalDateTime.now())
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
