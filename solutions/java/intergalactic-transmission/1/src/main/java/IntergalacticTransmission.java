import java.util.List;
import java.util.ArrayList;

public class IntergalacticTransmission {

    // ================= ENCODE =================
    public static List<Integer> getTransmitSequence(List<Integer> message) {

        List<Integer> allBits = new ArrayList<>();

        // 1. Alle Zahlen in Bits umwandeln
        for (int num : message) {
            for (int i = 7; i >= 0; i--) {
                int bit = (num >> i) & 1;
                allBits.add(bit);
            }
        }

        List<Integer> result = new ArrayList<>();

        // 2. Immer 7 Bits nehmen
        for (int i = 0; i < allBits.size(); i += 7) {

            List<Integer> group = new ArrayList<>();

            // 7 Bits sammeln
            for (int j = 0; j < 7; j++) {
                if (i + j < allBits.size()) {
                    group.add(allBits.get(i + j));
                } else {
                    group.add(0); // auffüllen mit 0
                }
            }

            // 3. Einsen zählen
            int ones = 0;
            for (int bit : group) {
                if (bit == 1) {
                    ones++;
                }
            }

            // 4. Paritätsbit
            int parity;
            if (ones % 2 == 0) {
                parity = 0;
            } else {
                parity = 1;
            }

            group.add(parity);

            // 5. Bits zurück zu Zahl
            int value = 0;
            for (int bit : group) {
                value = value * 2 + bit;
            }

            result.add(value);
        }

        return result;
    }

    // ================= DECODE =================
    public static List<Integer> decodeSequence(List<Integer> sequence) {

        List<Integer> dataBits = new ArrayList<>();

        for (int num : sequence) {

            List<Integer> bits = new ArrayList<>();

            // Zahl → Bits
            for (int i = 7; i >= 0; i--) {
                bits.add((num >> i) & 1);
            }

            // 1. Fehler prüfen (Parität)
            int ones = 0;
            for (int bit : bits) {
                if (bit == 1) {
                    ones++;
                }
            }

            if (ones % 2 != 0) {
                throw new IllegalArgumentException("Fehler in Übertragung");
            }

            // 2. Nur die ersten 7 Bits speichern
            for (int i = 0; i < 7; i++) {
                dataBits.add(bits.get(i));
            }
        }

        List<Integer> result = new ArrayList<>();

        // 3. Immer 8 Bits zurück zu Zahlen
        for (int i = 0; i + 7 < dataBits.size(); i += 8) {

            int value = 0;

            for (int j = 0; j < 8; j++) {
                value = value * 2 + dataBits.get(i + j);
            }

            result.add(value);
        }

        return result;
    }
}
