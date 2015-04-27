/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.fito.modele.pathfinding;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author arthur
 */
public class Graph {
    public Map<Position, Vecteur> graph;
    public List<Vecteur> z;
    
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
            this.getPosition(a.p1).voisins.put(this.getPosition(a.p2), a.difficulte);
        }
        
    }

    public void dijkstra(Position debut) {
        if(this.getPosition(debut) == null){
            System.out.println("Erreur dans le graph");
            return;
        }
        Vecteur source = this.getPosition(debut);
        List<Vecteur> q = new LinkedList<>();
        for(Vecteur v : graph.values()){
            v.precedent = v == source ? source : null;
            v.difficulte = v == source ? 0 : Integer.MAX_VALUE;
            q.add(v);
        }
        Collections.sort(q);
        dijkstra(q);   
    }

    private void dijkstra(List<Vecteur> q) {
        Vecteur u, v;
        z = new LinkedList<>();
        while(!q.isEmpty()){
            u = q.get(0);
            if(!z.contains(u)){
                z.add(u);
            }
            q.remove(u);
            if(u.difficulte == Integer.MAX_VALUE)
                break;
            for(Map.Entry<Vecteur, Integer> a : u.voisins.entrySet()){
                v = a.getKey();
                int alternateDifficulte = u.difficulte + a.getValue();
                if(alternateDifficulte < v.difficulte){
                    v.difficulte = alternateDifficulte;
                    v.precedent = u;
                    z.add(v);                  
                }
            }
            Collections.sort(q);
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
