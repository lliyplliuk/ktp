public class Point3d {
    /**
     * координата X
     **/
    private double xCoord;

    /**
     * координата Y
     **/
    private double yCoord;

    /**
     * координата Z
     **/
    private double zCoord;

    /**
     * Конструктор инициализации
     **/
    public Point3d(double x, double y, double z) {
        xCoord = x;
        yCoord = y;
        zCoord = z;
    }

    /**
     * Конструктор по
     * умолчанию .
     **/
    public Point3d() {
//Вызовите конструктор с двумя параметрами и определите источник.
        this(0, 0, 0);
    }

    /**
     * Возвращение координаты
     * X
     **/
    public double getX() {
        return xCoord;
    }

    /**
     * Возвращение координаты
     * Y
     **/
    public double getY() {
        return yCoord;
    }

    /**
     * Возвращение координаты
     * Z
     **/
    public double getZ() {
        return zCoord;
    }

    /**
     * Установка значения
     * координаты X.
     **/
    public void setX(double val) {
        xCoord = val;
    }

    /**
     * Установка значения
     * координаты Y.
     **/
    public void setY(double val) {
        yCoord = val;
    }

    /**
     * Установка значения
     * координаты Y.
     **/
    public void setZ(double val) {
        zCoord = val;
    }

    /**
     * Расстояние до другой точки
     */
    public double distanceTo(Point3d otherPoint) {
        return Math.sqrt(Math.pow(xCoord - otherPoint.getX(), 2) +
                Math.pow(yCoord - otherPoint.getY(), 2) +
                Math.pow(zCoord - otherPoint.getZ(), 2));
    }

    /**
     * Сравнение с другой точкой
     */
    public boolean equals(Point3d otherPoint) {
        return (xCoord == otherPoint.getX() &&
                yCoord == otherPoint.getY() &&
                zCoord == otherPoint.getZ());
    }
}
