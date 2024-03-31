package seed;

import domain.CardModel;

import java.time.LocalDate;

public class CardsSeedData {

    public static final CardModel card1 = new CardModel(
            1234567890123456L,
            "John Doe",
            123,
            LocalDate.of(2026, 12, 31),
            LocalDate.of(2021, 1, 1),
            true,
            true,
            5000,
            10000
    );

    public static final CardModel card2 = new CardModel(
            2345678901234567L,
            "Jane Smith",
            456,
            LocalDate.of(2027, 11, 30),
            LocalDate.of(2022, 2, 15),
            false,
            true,
            3000,
            7000
    );

    public static final CardModel card3 = new CardModel(
            3456789012345678L,
            "Alex Johnson",
            789,
            LocalDate.of(2028, 10, 30),
            LocalDate.of(2023, 3, 20),
            true,
            false,
            2000,
            5000
    );

    public static final CardModel card4 = new CardModel(
            4567890123456789L,
            "Chris Lee",
            101,
            LocalDate.of(2025, 9, 25),
            LocalDate.of(2020, 4, 25),
            true,
            true,
            4000,
            8000
    );
}
