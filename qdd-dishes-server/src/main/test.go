package main

import (
	"fmt"
)

type TreeNode struct {
	Value int
	Left  *TreeNode
	Right *TreeNode
}

func maxDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}

	left := maxDepth(root.Left)
	right := maxDepth(root.Right)

	if left > right {
		return left + 1
	}
	return right + 1
}

func maxValue(root *TreeNode) int {
	if root == nil {
		return 0
	}

	left := maxValue(root.Left)
	right := maxValue(root.Right)

	// left   right  root.val
	return maxTree(root.Value, left, right)
}
func maxTree(a, b, c int) int {
	if a > b {
		if a > c {
			return a
		}
		return c
	}
	if b > c {
		return b
	}
	return c
}

func main() {

	root := &TreeNode{Value: 1}
	root.Left = &TreeNode{Value: 2}
	root.Right = &TreeNode{Value: 3}
	root.Left.Left = &TreeNode{Value: 8}
	root.Left.Right = &TreeNode{Value: 5}
	root.Right.Right = &TreeNode{Value: 6}
	root.Right.Left = &TreeNode{Value: 7}
	root.Right.Left.Left = &TreeNode{Value: 4}

	depth := maxDepth(root)
	fmt.Println(depth)

	value := maxValue(root)
	fmt.Println("max", value)

}


朋友圈
	图片,文字 评论(是否好友)
				评论是否好友可见
朋友  关系
