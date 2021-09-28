package com.example.demo.src.polygon;
import java.awt.geom.Point2D;
import java.util.ArrayList;

class Polygon {
//	private static final String 	TAG = Polygon.class.getSimpleName();

    private ArrayList<Point2D.Double> mVertexs = new ArrayList<Point2D.Double>();

    public void addPoint(double xPos, double yPos) {
        mVertexs.add(new Point2D.Double(xPos, yPos));
    }

    public void clear() {
        mVertexs.clear();
    }

    public void printVertex() {
        for (Point2D.Double mVertex : mVertexs) {
            System.out.println(mVertex.x + ", " + mVertex.y);
        }
    }

    public boolean contains(double xPosf, double yPosf) {
        int sizeOfVertexs = mVertexs.size();

        if (sizeOfVertexs < 3) {
            return false;
        }

        int followIndex = sizeOfVertexs - 1;
        boolean isOddNodes = false;

        /**
         * 아래 알고리즘은 "Point in Polygon" 알고리즘이다.
         * 다만 좌우 양 방향을 체크하는 것이 아니라 왼쪽 방향만을 체크한다.
         */
        for (int frontIndex = 0; frontIndex < sizeOfVertexs; frontIndex++) {
            Point2D.Double frontPoint 	= mVertexs.get(frontIndex);
            Point2D.Double followPoint 	= mVertexs.get(followIndex);

            if (frontPoint.y < yPosf && followPoint.y >= yPosf
                    || followPoint.y < yPosf && frontPoint.y >= yPosf) {

                /**
                 * "직선의 기울기 m을 갖는 yPosf에 해당하는 x" < xPosf 인지 체크
                 * 두 점을 지나는 직선의 방정식 참고.
                 * 		y - y1 = M * (x - x1)
                 * 		M = (y2 - y1) / (x2 - x1)
                 */
                if (frontPoint.x + (yPosf - frontPoint.y) / (followPoint.y - frontPoint.y)
                        * (followPoint.x - frontPoint.x) < xPosf) {
                    isOddNodes = !isOddNodes;
                }
            }

            followIndex = frontIndex;
        }

        /**
         * "기울기 m을 갖는 yPosf에 해당하는 x" < xPosf의 개수가 홀수이면
         * 다각형안에 포함된 점이다.
         */
        return isOddNodes;


    }
    /*
    public static void main(String args[]) {
        Polygon polygon = new Polygon();
        polygon.addPoint(37.47756563818747, 127.14299349434039);
        polygon.addPoint(37.46886563818747, 127.14299349434039);
        polygon.addPoint(37.47386563818747, 127.14799349434039);

        polygon.printVertex();
        System.out.println(polygon.contains(37.4721713, 127.1428129));
    }

     */
}
