
package print;

import java.util.Collection;

public class print {

    protected int level = 0;

    public print (int level) {
        this.level = level;
    }

    public void changeLevel (int level) {
        this.level = level;
    }

    public int getLevel () {
        return level;
    }

    public static void print(Object... objects){
        StringBuilder sb = new StringBuilder();


        for(Object object : objects){
            if(object instanceof Collection){

                Collection<Object> collection = (Collection<Object>)object;
                sb.append(collectionUnraveler(collection));
            }
            else if (object.getClass().isArray()){
                sb.append(arrayUnraveler(object));
            }
            else{
                sb.append(object);
            }
            sb.append(" ");

        }

        System.out.println(sb.toString());
    }
    @SuppressWarnings("unchecked")
    private static StringBuilder collectionUnraveler(Collection<Object> collection){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        collection.forEach((x) -> {
            if(x instanceof Collection){
                Collection<Object> temp = ((Collection<Object>)x);
                sb.append(collectionUnraveler(temp));
            }
            else if (x.getClass().isArray()){

                sb.append(arrayUnraveler(x));
            }
            else{
                sb.append(x.toString());

            }
            sb.append(", ");
        });
        if (sb.length() > 1)
            sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb;
    }

    private static StringBuilder arrayUnraveler(Object array){
        StringBuilder sb = new StringBuilder();
        if(array instanceof Object[]){
                    Object[] arrayTemp = (Object[])array;
                    sb.append("[");
                    for (Object obj: arrayTemp){
                        sb.append(obj.toString());
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("]");
                }
                else if (array instanceof int[]){
                    int[] arrayTemp = (int[])array;
                    sb.append("[");
                    for (int i: arrayTemp){
                        sb.append(i);
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("]");
                }
                else if (array instanceof long[]){
                    long[] arrayTemp = (long[])array;
                    sb.append("[");
                    for (long i: arrayTemp){
                        sb.append(i);
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("]");
                }
                else if (array instanceof double[]){
                    double[] arrayTemp = (double[])array;
                    sb.append("[");
                    for (double i: arrayTemp){
                        sb.append(i);
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("]");
                }
                else if (array instanceof float[]){
                    float[] arrayTemp = (float[])array;
                    sb.append("[");
                    for (float i: arrayTemp){
                        sb.append(i);
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("]");
                }
                else if (array instanceof boolean[]){
                    boolean[] arrayTemp = (boolean[])array;
                    sb.append("[");
                    for (boolean i: arrayTemp){
                        sb.append(i);
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("]");
                }
                else if (array instanceof char[]){
                    char[] arrayTemp = (char[])array;
                    sb.append("[");
                    for (char i: arrayTemp){
                        sb.append(i);
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("]");
                }
                else if (array instanceof byte[]){
                    byte[] arrayTemp = (byte[])array;
                    sb.append("[");
                    for (byte i: arrayTemp){
                        sb.append(i);
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("]");
                }
                else if (array instanceof short[]){
                    short[] arrayTemp = (short[])array;
                    sb.append("[");
                    for (short i: arrayTemp){
                        sb.append(i);
                        sb.append(", ");
                    }
                    sb.delete(sb.length() - 2, sb.length());
                    sb.append("]");
                }
                return sb;
            }
    
    public void log(int priority, Object... objects){
        if(priority >= level){
            print(objects);
        }
    }
}
