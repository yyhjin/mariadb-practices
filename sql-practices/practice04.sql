--
-- 혼합 SQL 문제입니다. (서브쿼리(SUBQUERY) 포함)
--

-- 문제1.
-- 현재, 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까?
select count(*)
from salaries
where salary > (select avg(salary)
				from salaries
                where to_date = '9999-01-01')
and to_date = '9999-01-01';

-- 문제2. (X)
-- 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다. 

-- 문제3.
-- 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요 
select e.emp_no, e.first_name, s.salary, a.dept_name
from employees e, salaries s, dept_emp de, (select d.dept_no as dept_no, avg(salary) as avg_salary, d.dept_name as dept_name
											from departments d, dept_emp de, salaries s
											where d.dept_no = de.dept_no
											and de.emp_no = s.emp_no
											and de.to_date = '9999-01-01'
											and s.to_date = '9999-01-01'
											group by d.dept_no) a
where e.emp_no = s.emp_no
and e.emp_no = de.emp_no
and s.to_date = '9999-01-01'
and de.to_date = '9999-01-01'
and de.dept_no = a.dept_no
and s.salary > a.avg_salary;

-- 문제4.
-- 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요.
-- 직원) employees + dept
-- 매니저) dept_manager + employees
-- 둘이 연결할떄는 dept + dept_manager로
select e.emp_no, e.first_name, a.first_name as manager, d.dept_name
from departments d, dept_emp de, employees e, (select dm.dept_no, e.first_name
											   from dept_manager dm, employees e
                                               where dm.emp_no = e.emp_no
                                               and to_date = '9999-01-01') a
where d.dept_no = de.dept_no
and de.emp_no = e.emp_no
and a.dept_no = d.dept_no
and de.to_date = '9999-01-01';


-- join문제임?

-- 문제5.
-- 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요.
select e.emp_no, e.first_name, t.title, s.salary
from dept_emp de, employees e, titles t, salaries s
where de.emp_no = e.emp_no
and e.emp_no = t.emp_no
and e.emp_no = s.emp_no
and de.to_date = '9999-01-01'
and t.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
and de.dept_no = (select d.dept_no
				  from departments d, dept_emp dm, salaries s
                  where d.dept_no = dm.dept_no
                  and dm.emp_no = s.emp_no
                  group by d.dept_no
                  order by avg(s.salary) desc
                  limit 1)
order by s.salary desc;

-- 문제6.
-- 평균 연봉이 가장 높은 부서는? 
-- 총무 20000
select d.dept_name, avg(s.salary)
from departments d, dept_emp de, salaries s
where d.dept_no = de.dept_no
and de.emp_no = s.emp_no
group by d.dept_name
order by avg(s.salary) desc
limit 1;

-- 문제7.
-- 평균 연봉이 가장 높은 직책?
-- Engineer 40000
select t.title, avg(s.salary)
from titles t, salaries s
where t.emp_no = s.emp_no
group by t.title
order by avg(s.salary) desc
limit 1;

-- 문제8. (순수 join 문제)
-- 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은?
-- 부서이름, 사원이름, 연봉, 매니저 이름, 매니저 연봉 순으로 출력합니다.
select d.dept_name, e.first_name, s.salary, a.first_name, a.salary
from departments d, dept_emp de, employees e, salaries s, (select dm.dept_no, s.salary, e.first_name
														   from dept_manager dm, employees e, salaries s
                                                           where dm.emp_no = e.emp_no
                                                           and e.emp_no = s.emp_no
                                                           and dm.to_date = '9999-01-01'
                                                           and s.to_date = '9999-01-01') a
where d.dept_no = de.dept_no
and de.emp_no = e.emp_no
and e.emp_no = s.emp_no
and de.to_date = '9999-01-01'
and s.to_date = '9999-01-01'
and de.dept_no = a.dept_no
and s.salary > a.salary;
