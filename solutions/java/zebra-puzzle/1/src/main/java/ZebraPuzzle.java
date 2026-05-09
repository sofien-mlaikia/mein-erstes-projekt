import java.util.ArrayList;
import java.util.List;

class ZebraPuzzle {

    // --- Indizes für jede Kategorie ---
    // Nationalitäten
    private static final int ENG = 0, SPA = 1, UKR = 2, NOR = 3, JAP = 4;
    // Farben
    private static final int RED = 0, GRN = 1, IVR = 2, YEL = 3, BLU = 4;
    // Getränke
    private static final int COF = 0, TEA = 1, MLK = 2, OJU = 3, WAT = 4;
    // Haustiere
    private static final int DOG = 0, SNL = 1, FOX = 2, HRS = 3, ZBR = 4;
    // Hobbys
    private static final int PNT = 0, DNC = 1, RDG = 2, FBL = 3, CHS = 4;

    private static final String[] NAMES = {
        "Englishman", "Spaniard", "Ukrainian", "Norwegian", "Japanese"
    };

    // Alle 120 Permutationen von [1,2,3,4,5] vorab berechnen
    private static final List<int[]> PERMS = buildPermutations();

    private String waterDrinker;
    private String zebraOwner;

    ZebraPuzzle() {
        solve();
    }

    private void solve() {
        // nat[i] = Hausnummer (1-5) der i-ten Nationalität
        for (int[] nat : PERMS) {
            if (nat[NOR] != 1) continue;           // Regel 10: Norweger in Haus 1

            for (int[] col : PERMS) {
                if (col[RED] != nat[ENG]) continue;             // Regel 2: Engländer → Rotes Haus
                if (col[GRN] != col[IVR] + 1) continue;        // Regel 6: Grün direkt rechts von Elfenbein
                if (Math.abs(nat[NOR] - col[BLU]) != 1) continue; // Regel 15: Norweger neben Blau

                for (int[] drk : PERMS) {
                    if (drk[MLK] != 3) continue;                // Regel 9: Mittleres Haus → Milch
                    if (drk[COF] != col[GRN]) continue;         // Regel 4: Gewächshaus → Kaffee
                    if (drk[TEA] != nat[UKR]) continue;         // Regel 5: Ukrainer → Tee

                    for (int[] hob : PERMS) {
                        if (col[YEL] != hob[PNT]) continue;     // Regel 8: Gelbes Haus → Maler
                        if (hob[FBL] != drk[OJU]) continue;     // Regel 13: Fußball → OJ
                        if (nat[JAP] != hob[CHS]) continue;     // Regel 14: Japaner → Schach

                        for (int[] pet : PERMS) {
                            if (pet[DOG] != nat[SPA]) continue;  // Regel 3: Spanier → Hund
                            if (pet[SNL] != hob[DNC]) continue;  // Regel 7: Schnecke → Tanzen
                            if (Math.abs(hob[RDG] - pet[FOX]) != 1) continue; // Regel 11
                            if (Math.abs(hob[PNT] - pet[HRS]) != 1) continue; // Regel 12

                            // ✅ Lösung gefunden! Jetzt Ergebnisse auslesen
                            for (int i = 0; i < 5; i++) {
                                if (nat[i] == drk[WAT]) waterDrinker = NAMES[i];
                                if (nat[i] == pet[ZBR]) zebraOwner  = NAMES[i];
                            }
                            return; // Fertig!
                        }
                    }
                }
            }
        }
    }

    // Alle Permutationen von {1,2,3,4,5} generieren (120 Stück)
    private static List<int[]> buildPermutations() {
        List<int[]> list = new ArrayList<>();
        permute(new int[]{1, 2, 3, 4, 5}, 0, list);
        return list;
    }

    private static void permute(int[] arr, int start, List<int[]> result) {
        if (start == arr.length) {
            result.add(arr.clone()); // Kopie speichern!
            return;
        }
        for (int i = start; i < arr.length; i++) {
            // Tausch
            int tmp = arr[start]; arr[start] = arr[i]; arr[i] = tmp;
            permute(arr, start + 1, result);
            // Rücktausch (Backtracking)
            tmp = arr[start]; arr[start] = arr[i]; arr[i] = tmp;
        }
    }

    String getWaterDrinker() { return waterDrinker; }
    String getZebraOwner()   { return zebraOwner; }
}