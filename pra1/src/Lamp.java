public enum Lamp {
    // 每一个枚举元素各表示一个方向的控制灯
    S2N("N2S", "S2W", false), S2W("N2E", "E2W", false), E2W("W2E", "E2S", false), E2S("W2N", "S2N", false),
    // 以下元素表示与上面的元素的相反方向的灯，它们的“相反方向灯”和“下一个灯”应忽略不计！
    N2S(null, null, false), N2E(null, null, false), W2E(null, null, false), W2N(null, null, false),
    // 由南向东和由西向北等右拐弯的灯不受红绿灯的控制，所以，能够假想它们总是绿灯
    S2E(null, null, true), E2N(null, null, true), N2W(null, null, true), W2S(null, null, true);

    private Lamp(String opposite, String next, boolean lighted) {
        this.opposite = opposite;
        this.next = next;
        this.lighted = lighted;
    }

    // 当前灯是否为绿
    private boolean lighted;

    // 与当前灯同一时候为绿的相应方向
    private String opposite;

    // 当前灯变红时下一个变绿的灯
    private String next;

    public boolean isLighted() {
        return lighted;
    }

    /**
     * 某个灯变绿时。它相应方向的灯也要变绿
     */
    public void light() {
        this.lighted = true;
        if (opposite != null) {
            Lamp.valueOf(opposite).light();
        }
        System.out.println(name() + " lamp is green。以下总共应该有6个方向能看到汽车穿过！");
    }

    /**
     * 某个灯变红时，相应方向的灯也要变红，而且下一个方向的灯要变绿
     * @return 下一个要变绿的灯
     */
    public Lamp blackOut() {
        this.lighted = false;
        if (opposite != null) {
            Lamp.valueOf(opposite).blackOut();
        }

        Lamp nextLamp = null;
        if (next != null) {
            nextLamp = Lamp.valueOf(next);
            System.out.println("绿灯从" + name() + "-------->切换为" + next);
            nextLamp.light();
        }
        return nextLamp;
    }
}