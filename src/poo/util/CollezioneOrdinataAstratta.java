package poo.util;

import java.util.Iterator;

public abstract class CollezioneOrdinataAstratta<T extends Comparable<? super T>> implements CollezioneOrdinata<T>{
    public boolean equals(Object x){
        if(!(x instanceof CollezioneOrdinata))return false;
        if(x==this)return true;
        CollezioneOrdinata<T> co=(CollezioneOrdinata)x;
        if(co.size()!=this.size())return false;
        Iterator<T>it1=iterator(),it2=co.iterator();
        while(it1.hasNext()){
            if(!(it1.next().equals(it2.next())))return false;
        }//while
        return true;
    }//equals

    public int hashCode(){
        int h=0;
        int M=883;
        for(T e:this){
            h=h*M+e.hashCode();
        }//for
        return h;
    }//hashCode

    public String toString(){
        StringBuilder sb= new StringBuilder(300);
        sb.append("[");
        Iterator<T> i= iterator();
        while(i.hasNext()){
            sb.append(i.next());
            if(i.hasNext()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }//toString
}//CollezioneOrdinataAstratta
