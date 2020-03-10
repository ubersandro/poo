package poo.util;

public interface Stack<T> extends Iterable<T> {
	/*
	 * Posso implementarli tutti tranne push TODO
	 * comportamento LIFO
	 */
	int size();
	void clear();
	void push(T e);//inserimento in top allo stack 
	T pop();//remove a tutti gli effetti ==> fallisce se lo stack e' vuoto
	T top(); //non rimuove l'elemento che c'e' in cima ma lo restituisce
	boolean isEmpty();
	boolean isFull();
}//Stack<T>
