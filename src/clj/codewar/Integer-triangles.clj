;You have to give the number of different integer triangles with one angle of 120 degrees which perimeters are under or equal a certain value. Each side of an integer triangle is an integer value.
;
;give_triang(max. perimeter) --------> number of integer triangles,
;with sides a, b, and c integers such that:
;
;a + b + c <= max. perimeter
;
;See some of the following cases
;
;give_triang(5) -----> 0 # No Integer triangles with perimeter under or equal five
;give_triang(15) ----> 1 # One integer triangle of (120 degrees). It's (3, 5, 7)
;give_triang(30) ----> 3 # Three triangles: (3, 5, 7), (6, 10, 14) and (7, 8, 13)
;give_triang(50) ----> 5 # (3, 5, 7), (5, 16, 19), (6, 10, 14), (7, 8, 13) and (9, 15, 21) are the triangles with perim under or equal 50.

(defn give-triang [per]
  ; your code
  )