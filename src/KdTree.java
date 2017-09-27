import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private Node root;
    private boolean horizontal;

    private class Node {
        private Point2D val;
        private Node left, right;
        private int n;

        public Node(double key, Point2D val, int n) {
            this.val = val;
            this.n = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void insert(Point2D point) {
        if (point == null)
            throw new NullPointerException();
        horizontal = true;
        root = insert(root, point, horizontal);
    }

    private Node insert(Node x, Point2D point, boolean h) {
        if (point == null)
            throw new NullPointerException();
        if (x == null) {
            if (h)
                return new Node(point.x(), point, 1);
            else
                return new Node(point.y(), point, 1);
        }
        if (!(x.val.equals(point))) {
            if (h) {
                if (point.x() < x.val.x()) {
                    x.left = insert(x.left, point, !h);
                } else {
                    x.right = insert(x.right, point, !h);
                }
            } else {
                if (point.y() < x.val.y()) {
                    x.left = insert(x.left, point, !h);
                } else {
                    x.right = insert(x.right, point, !h);
                }
            }
            x.n = size(x.left) + size(x.right) + 1;
        }
        return x;
    }

    public boolean contains(Point2D point) {
        if (point == null)
            throw new NullPointerException();
        horizontal = true;
        return contains(root, point, horizontal);
    }

    private boolean contains(Node x, Point2D p, boolean h) {
        if (x == null)
            return false;
        if (x.val.equals(p))
            return true;
        else if (h) {
            if (p.x() < x.val.x())
                return contains(x.left, p, !h);
            else
                return contains(x.right, p, !h);
        } else {
            if (p.y() < x.val.y())
                return contains(x.left, p, !h);
            else
                return contains(x.right, p, !h);
        }
    }

    public void draw() {
        horizontal = true;
        draw(root, horizontal);
    }

    private void draw(Node x, boolean h) {
        if (x != null) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.point(x.val.x(), x.val.y());
            if (h) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(x.val.x(), 0.0, x.val.x(), 1.0);
            } else {
                StdDraw.setPenColor(StdDraw.BLUE);
                StdDraw.line(0.0, x.val.y(), 1.0, x.val.y());
            }
            draw(x.left, !h);
            draw(x.right, !h);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null)
            throw new NullPointerException();
        horizontal = true;
        TreeSet<Point2D> inRect = new TreeSet<Point2D>();

        return range(root, rect, horizontal, inRect);
    }

    private Iterable<Point2D> range(Node x, RectHV rect, boolean h, TreeSet<Point2D> treeSet) {
        if (x == null)
            return treeSet;
        if (h) {
            if (x.val.x() < rect.xmin())
                return range(x.right, rect, !h, treeSet);
            if (x.val.x() > rect.xmax())
                return range(x.left, rect, !h, treeSet);
            else {
                if (x.val.y() <= rect.ymax() && x.val.y() >= rect.ymin())
                    treeSet.add(x.val);
                TreeSet<Point2D> leftSide = new TreeSet<Point2D>();
                TreeSet<Point2D> rightSide = new TreeSet<Point2D>();
                leftSide = (TreeSet<Point2D>) range(x.left, rect, !h, leftSide);
                rightSide = (TreeSet<Point2D>) range(x.right, rect, !h, rightSide);
                treeSet.addAll(leftSide);
                treeSet.addAll(rightSide);
                return treeSet;
            }
        } else {
            if (x.val.y() < rect.ymin())
                return range(x.right, rect, !h, treeSet);
            if (x.val.y() > rect.ymax())
                return range(x.left, rect, !h, treeSet);
            else {
                if (x.val.x() <= rect.xmax() && x.val.x() >= rect.xmin())
                    treeSet.add(x.val);
                TreeSet<Point2D> leftSide = new TreeSet<Point2D>();
                TreeSet<Point2D> rightSide = new TreeSet<Point2D>();
                leftSide = (TreeSet<Point2D>) range(x.left, rect, !h, leftSide);
                rightSide = (TreeSet<Point2D>) range(x.right, rect, !h, rightSide);
                treeSet.addAll(leftSide);
                treeSet.addAll(rightSide);
                return treeSet;
            }
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null)
            throw new NullPointerException();
        if (isEmpty())
            return null;
        boolean horizontal = true;
        Point2D closestSoFar;
        closestSoFar = root.val;
        double startingDistance = p.distanceTo(root.val);
        return nearest(root, p, startingDistance, horizontal, closestSoFar);
    }

    private Point2D nearest(Node x, Point2D p, double startingDistance, boolean h, Point2D tempCloeset) {
        if (x == null)
            return tempCloeset;
        Point2D alternativePoint = null;
        if (startingDistance > x.val.distanceTo(p)) {
            tempCloeset = x.val;
            startingDistance = x.val.distanceTo(p);
        } else if (startingDistance == x.val.distanceTo(p) && !tempCloeset.equals(x.val))
            alternativePoint = x.val;

        if (h) {
            double horizontalDistance = Math.abs(p.x() - x.val.x());
            if (p.x() < x.val.x()) {
                tempCloeset = nearest(x.left, p, startingDistance, !h, tempCloeset);
                if (horizontalDistance <= startingDistance)
                    tempCloeset = nearest(x.right, p, startingDistance, !h, tempCloeset);
            } else {
                tempCloeset = nearest(x.right, p, startingDistance, !h, tempCloeset);
                if (horizontalDistance <= startingDistance)
                    tempCloeset = nearest(x.left, p, startingDistance, !h, tempCloeset);
            }
        } else {
            double virticalDistance = Math.abs(p.y() - x.val.y());
            if (p.y() < x.val.y()) {
                tempCloeset = nearest(x.left, p, startingDistance, !h, tempCloeset);
                if (virticalDistance <= startingDistance)
                    tempCloeset = nearest(x.right, p, startingDistance, !h, tempCloeset);
            } else {
                tempCloeset = nearest(x.right, p, startingDistance, !h, tempCloeset);
                if (virticalDistance <= startingDistance)
                    tempCloeset = nearest(x.left, p, startingDistance, !h, tempCloeset);
            }
        }

        double shortestDistanceSoFar = startingDistance;
        if (alternativePoint == null)
            return tempCloeset;
        else {
            alternativePoint = nearest(x, p, startingDistance, h, alternativePoint);
            if (startingDistance < shortestDistanceSoFar)
                return alternativePoint;
            else
                return tempCloeset;
        }
    }

    public static void main(String[] args) {
    }
}
