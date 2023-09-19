package gh2;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdAudio;

public class GuitarHero {
    public static void main(String[] args) {
        String keyboard="1q2w3e4r5t6y7u8i9op-[=azsxdcfvgbhnjmk,.;/' ";
        int numKeys = keyboard.length();

        GuitarString[] strings = new GuitarString[numKeys];

        for (int i = 0; i < numKeys; i++) {
            double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
            strings[i] = new GuitarString(frequency);
        }



        while (true) {
            // Check if the user has typed a key; if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index != -1) {
                    strings[index].pluck();
                }
            }

            // Compute the superposition of samples
            double sample = 0.0;
            for (int i = 0; i < numKeys; i++) {
                sample += strings[i].sample();
            }

            // Play the sample on standard audio
            StdAudio.play(sample);

            // Advance the simulation of each guitar string by one step
            for (int i = 0; i < numKeys; i++) {
                strings[i].tic();
            }
        }
    }

    }
