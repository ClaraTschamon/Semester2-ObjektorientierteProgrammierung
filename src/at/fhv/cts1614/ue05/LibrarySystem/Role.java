//Clara Tschamon, Gruppe1
package at.fhv.cts1614.ue05.LibrarySystem;

public abstract class Role {
    private final int _lendingtimeBooks;
    private final int _lendingtimeOthers;
    private final float _lendingfee;
    private final int MAXEXTENSIONS;

    public Role(int lendingtimeBooks, int lendingtimeOthers, float lendingfee, int maxextensions){
        _lendingtimeBooks = lendingtimeBooks;
        _lendingtimeOthers = lendingtimeOthers;
        _lendingfee = lendingfee;
        MAXEXTENSIONS = maxextensions;
    }

    public int get_lendingtimeBooks() {
        return _lendingtimeBooks;
    }

    public int get_lendingtimeOthers() {
        return _lendingtimeOthers;
    }

    public float get_lendingfee() {
        return _lendingfee;
    }

    public int getMAXEXTENSIONS() {
        return MAXEXTENSIONS;
    }
}
