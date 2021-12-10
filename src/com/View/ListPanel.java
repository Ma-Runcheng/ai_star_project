package com.View;

import com.Algorithm.Astar;
import com.Algorithm.Node;
import com.Observer;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

public class ListPanel extends JPanel implements Observer{
    Astar astar;
    public JTable table;
    public JScrollPane scrollPane;
    Object[] head = {"OpenList","CloseList"};
    Object[][] rowData =  new Object[400][400];
    List<Node> closeList = null;
    Queue<Node> openList = null;

    public ListPanel(Astar astar) {
        this.astar = astar;
        astar.register(this);
        setLayout(new BorderLayout());
        table = new JTable(rowData,head);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public void setAstar(Astar astar) {
        this.astar = astar;
        astar.register(this);
    }

    @Override
    public void update() {
        openList = astar.getOpenList();
        closeList = astar.getCloseList();
        int index = 0;
        Object[] open = openList.toArray();
        Arrays.sort(open);

        for(Object Node : open){
            Node node = (Node) Node;
            String nodePos = "("+node.pos.x+","+node.pos.y+")"+"  F= "+node.F;
            table.getModel().setValueAt(nodePos,index++,0);
        }
        while(index < 400){
            table.getModel().setValueAt("",index++,0);
        }
        index = 0;
        for(Node node : closeList){
            String nodePos = "("+node.pos.x+","+node.pos.y+")";
            table.getModel().setValueAt(nodePos,index++,1);
        }
        while(index < 400){
            table.getModel().setValueAt("",index++,1);
        }
    }
}
