-- inner join

-- 예제1) 현재 근무하고 있는 직원 사번, 이름과 직책을 모두 출력
select e.emp_no, e.first_name, t.title
from employees e, titles t
where e.emp_no = t.emp_no		# join 조건 (n-1)
and t.to_date = '9999-01-01';	# row 선택 조건
