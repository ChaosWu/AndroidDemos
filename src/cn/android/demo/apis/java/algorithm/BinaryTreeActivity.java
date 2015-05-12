package cn.android.demo.apis.java.algorithm;

import cn.android.demo.apis.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 二叉树
 * 
 * @author Elroy
 * 
 */
public class BinaryTreeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		setContentView(tv);

		BinaryTree bt = new BinaryTree();
		bt.createBinTree(bt.root);
		
		bt.preOrder(bt.root);
		
		bt.inOrder(bt.root);
		
		bt.postOrder(bt.root);
		
		
		bt.nonRecPreOrder(bt.root);
		
		bt.nonRecInOrder(bt.root);
		
		bt.nonRecPostOrder(bt.root);
		
	}
}
