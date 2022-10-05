public class CPU {
    private int gen;
    private String series;
    private double price;
    private double speed;
    private String launchDate;
    private boolean SGXSupport;

    public CPU() {
        gen = 1;
        series = "i3";
        price = 117;
        speed = 2.93;
        launchDate = "Q1'10";
        SGXSupport = false;
    }

    public CPU(int gen, String series, double price, double speed, String launchDate, boolean SGXSupport) {
        if( !(gen > 0 || gen < 10 && (series == "i3" || series == "i5" || series == "i7" || series == "i9") && price > 0 && speed > 0))
            throw new IllegalArgumentException("");
        this.gen = gen;
        this.series = series;
        this.price = price;
        this.speed = speed;
        this.launchDate = launchDate;
        this.SGXSupport = SGXSupport;
    }

    public int getGen() {
        return this.gen;
    }

    public String getSeries() {
        return this.series;
    }

    public double getPrice() {
        return this.price;
    }

    public double getSpeed() {
        return this.speed;
    }

    public String getLaunchDate() {
        return this.launchDate;
    }

    public boolean getSGXSupport() {
        return this.SGXSupport;
    }

    public void setPrice(double newPrice) {
        price = newPrice;
    }

    private int compareDate(String date1, String date2) {
        int year1 = Integer.parseInt(date1.substring(3, 4));
        int year2 = Integer.parseInt(date2.substring(3, 4));
        if (year1 > year2)
            return 1;
        else if (year1 < year2)
            return -1;
        else {
            if (date1.charAt(1) > date2.charAt(1))
                return 1;
            else if (date1.charAt(1) < date2.charAt(1))
                return -1;
            else
                return 0;
        }
    }

    public double priceNow(String sQuarterYear) {
        int compare = compareDate(launchDate, sQuarterYear);
        if (compare >= 0)
            return price;
        else {
            int quarterCount = 0;
            int year1 = Integer.parseInt(launchDate.substring(3, 5));
            int year2 = Integer.parseInt(sQuarterYear.substring(3, 5));
            quarterCount += (4 * (year2 - year1));
            int quarter1 = Integer.parseInt(launchDate.substring(1, 2));
            int quarter2 = Integer.parseInt(sQuarterYear.substring(1, 2));
            quarterCount += (quarter2 - quarter1);
            double newPrice = price - (price * 0.02 * quarterCount);
            if (newPrice < 0)
                throw new IllegalArgumentException("");
            else
                return newPrice;
        }
    }

    @Override
    public String toString() {
        if (SGXSupport)
            return "This CPU is " + this.gen + "th generation (" + this.speed + "GHz), launched: "
                    + this.launchDate + " with price: " + price + " USD. SGX is supported.";
        else
            return "This CPU is " + this.gen + "th generation (" + this.speed + "GHz), launched: "
                    + this.launchDate + " with price: " + price + " USD. SGX is not supported.";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null)
            return false;
        if (this == object)
            return true;
        if (this.getClass() != object.getClass())
            return false;
        CPU cpu = (CPU) object;
        return this.gen == cpu.gen && this.series.toLowerCase().equals(cpu.series.toLowerCase())
                && this.SGXSupport == cpu.SGXSupport;
    }
    
    public boolean isHigherGeneration(CPU cpu) {
        return this.gen > cpu.gen;
    }
    
    public static void main(String[] args) {
        System.out.println("Welcome to the simple class example!\n");
        CPU c1 = new CPU();
        CPU c2 = new CPU(10, "i9", 449.00, 3.1, "Q2'19", true);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(String.format("CPU1 Series: %s", c1.getSeries()));
        System.out.println(String.format("CPU1 suggested price: %.2f USD", c1.getPrice()));
        c1.setPrice(110.0);
        System.out.println(String.format("CPU1 suggested price (after mutator call): %.2f USD", c1.getPrice()));
        System.out.println(String.format("Are CPU1 and CPU2 equal? %s", c1.equals(c2) ? "YES": "NO"));
        System.out.println(String.format("Is CPU1 higher generation than CPU2? %s", c1.isHigherGeneration(c2) ? "YES": "NO"));
        System.out.println(String.format("CPU1 Price at Q3'19: %.2f USD", c1.priceNow("Q3'19")));
        System.out.println(String.format("CPU2 Price at Q3'19: %.2f USD", c2.priceNow("Q3'19")));
        System.out.println("\nThank you for testing the simple class example!");
    }
}
