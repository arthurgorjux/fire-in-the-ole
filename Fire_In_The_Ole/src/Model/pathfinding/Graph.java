/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.pathfinding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 *
 * @author arthur
 */
public class Graph {
    public Map<Position, Vecteur> graph;
    
    public Graph(List<Arete> aretes){
        graph = new HashMap<>();
        for(Arete a : aretes){            
            if(this.getPosition(a.p1) == null){
                graph.put(a.p1, new Vecteur(a.p1));
            }

            if(this.getPosition(a.p2) == null){
                graph.put(a.p2, new Vecteur((a.p2)));
            }
        }
        
        for(Arete a : aretes){
            this.getPosition(a.p2).voisins.put(this.getPosition(a.p1), a.difficulte);
        }
    }

    public NavigableSet<Vecteur> dijkstra(Position debut) {
        if(this.getPosition(debut) == null){
            System.out.println("Erreur dans le graph");
            return null;
        }
        Vecteur source = this.getPosition(debut);
        NavigableSet<Vecteur> q = new TreeSet<>();
        for(Vecteur v : graph.values()){
            v.precedent = v == source ? source : null;
            v.difficulte = v == source ? 0 : Integer.MAX_VALUE;
            System.out.println(v);
            
            System.out.println(q.add(v));
        }
        
        dijkstra(q);
        return q;
    }

    private void dijkstra(NavigableSet<Vecteur> q) {
        Vecteur u, v;
        while(!q.isEmpty()){
            u = q.pollFirst();
            if(u.difficulte == Integer.MAX_VALUE)
                break;
            for(Map.Entry<Vecteur, Integer> a : u.voisins.entrySet()){
                v = a.getKey();
                int alternateDifficulte = u.difficulte + a.getValue();
                if(alternateDifficulte < v.difficulte){
                    q.remove(v);
                    v.difficulte = alternateDifficulte;
                    v.precedent = u;
                    q.add(v);
                }
            }
        }
    }
    
    private boolean containsKey(Position pos){
        for(Position p : graph.keySet()){
            if(p.getX() == pos.getX() && p.getY() == pos.getY()){
                return true;
            }
            return false;
        }       
        return false;
    }
    
    private Vecteur getPosition(Position key){
        Vecteur result = null;
        for(Position p : graph.keySet()){
            if(p.getX() == key.getX() && p.getY() == key.getY()){
                result = graph.get(p);
            }
        }
        return result;
    }
}
