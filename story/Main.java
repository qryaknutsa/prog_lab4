package story;

import java.util.Scanner;

public class Main {
    static int time;
    static String weather;
    static void checkOfCondition() throws WeatherException {
        System.out.println();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выбранную вами погоду (солнце, дождь, туман, сильный ветер, немного облачно): ");
        weather = in.nextLine();
        System.out.print("Введите время в точности до часа: ");
        time = in.nextInt();
        if((time>24 | time<0)|(!weather.contains("олнце") & !weather.contains("блачно") & !weather.contains("етер") & !weather.contains("ождь") & !weather.contains("уман"))){
            throw new IncrorrectValueException("Вы ввели неравильное значение. Попробуйте ввести снова.");
        }
        if (time > 20 | time < 4) {
            throw new TimeException("На улице слишком темно и опасно, чтобы лазить по крышам. Никто никуда не пошёл.");
        }
        if (!weather.contains("солнце") & !weather.contains("облачно")) {
            throw new WeatherException("Погода плохая." + " На улице " + weather+".");
        }

    }

    public static void main(String[] args) throws WeatherException {
        System.out.println(" ");
        Kid m = new Kid("Малыш", 1, 1.5f, false);
        FlyingMan k = new FlyingMan("Карлсон", 2, Varenie.MALINA, false);
        m.emergence();
        k.emergence();

        Main main = new Main();
        try {
            checkOfCondition();
        } catch (TimeException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }catch (IncrorrectValueException e){
            System.out.println(" ");
            System.out.println(e.getMessage());
            Scanner in = new Scanner(System.in);
            System.out.print("Введите выбранную вами погоду (солнце, дождь, туман, сильный ветер, немного облачно): ");
            weather = in.nextLine();
            System.out.print("Введите время в точности до часа: ");
            time = in.nextInt();
        }
        catch (WeatherException e) {
            System.out.println(" ");
            System.out.println(e.getMessage());
            if(weather.contains("ождь") ){
                m.setClothes("дождевик");
                k.setClothes("дождевик");
                System.out.println(m.getName()+" надел дождевик, и теперь может погулять по крышам.");
                System.out.println(k.getName()+" надел дождевик, и теперь может погулять по крышам.");
            }
            if(weather.contains("етер")){
                m.setClothes("Тёплая кофта.");
                k.setClothes("Тёплая кофта.");
                System.out.println(m.getName()+" надел тёплую кофту, и теперь может погулять по крышам.");
                System.out.println(k.getName()+" надел тёплую кофту, и теперь может погулять по крышам.");
            }
            if(weather.contains("уман")){
                m.setClothes("Специальные очки");
                k.setClothes("Специальные очки");
                System.out.println(m.getName()+" надел специальныю очки, и теперь может погулять по крышам.");
                System.out.println(k.getName()+" надел специальныю очки, и теперь может погулять по крышам.");
            }
        }


        System.out.println(" ");
        for (int i = 0; i < Noise.values().length; i++) {
            Buildings b = new Buildings();
            final Noise noise = Noise.getNoise(i);
            Buildings.Windows w = b.new Windows();
            Noisable n = new Noisable() {
                @Override
                public String makeNoise(Buildings.Windows windows, Noise noise, Integer t) {
                    return "В " + t.toString() +"-ом окне " + noise.getRussianNoise();
                }
            };
            System.out.println(n.makeNoise(w, noise, i + 1));
        }
        System.out.println(" ");

        final Varenie varenie = Varenie.getRandomVarenie();
        Kid.offerJam(varenie, m, k);

        System.out.println(" ");


        Buildings[] b = new Buildings[10];
        for (int i = 0; i < 5; i++) {
            b[i] = new Buildings();
            b[i].generateLengthOfHole();
            b[i].generateLengthOfRoof();
            k.move(k, b[i].getLengthRoof());

            m.move(m, b[i].getLengthRoof());


            if (m.falling(m, b[i].getLengthHole())) {
                k.saving(k, m);
                break;
            } else m.move(m, b[i].getLengthRoof());
        }
    }
}
