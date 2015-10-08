package com.enginemobi.core.common;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collection;


public class Criterion
{
    private final Object arg1;
    private final Operator operator;
    private final Object compareTo;

    public Criterion(Object arg1, Operator operator, Object compareTo)
    {
        this.arg1 = arg1;
        this.operator = operator;
        this.compareTo = compareTo;
    }

    public Criterion getArg1Criterion()
    {
        if (arg1 instanceof Criterion){
            return (Criterion) this.arg1;
        }else{
            throw new IllegalArgumentException(arg1.toString());
        }
    }

    public Criterion getArg2Criterion()
    {
        if (compareTo instanceof Criterion){
            return (Criterion) this.compareTo;
        }else{
            throw new IllegalArgumentException(compareTo.toString());
        }
    }

    public String getPropertyName()
    {
        if (arg1 instanceof String) {
            return (String) this.arg1;
        }else{
            throw new IllegalArgumentException(arg1.toString());
        }
    }

    public Operator getOperator()
    {
        return this.operator;
    }

    public Object getCompareTo()
    {
        return this.compareTo;
    }

    public static enum Operator
    {
        EQ {
            @Override
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                return b.equal(r.get(c.getPropertyName()), c.getCompareTo());
            }
        },
        NEQ {
            @Override
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                return b.notEqual(r.get(c.getPropertyName()), c.getCompareTo());
            }
        },
        LT {
            @Override @SuppressWarnings("unchecked")
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                return b.lessThan(
                        r.<Comparable>get(c.getPropertyName()), getComparable(c)
                );
            }
        },
        LTE {
            @Override @SuppressWarnings("unchecked")
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                return b.lessThanOrEqualTo(
                        r.<Comparable>get(c.getPropertyName()), getComparable(c)
                );
            }
        },
        GT {
            @Override @SuppressWarnings("unchecked")
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                return b.greaterThan(
                        r.<Comparable>get(c.getPropertyName()), getComparable(c)
                );
            }
        },
        GTE {
            @Override @SuppressWarnings("unchecked")
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                return b.greaterThanOrEqualTo(
                        r.<Comparable>get(c.getPropertyName()), getComparable(c)
                );
            }
        },
        LIKE {
            @Override
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                return b.like(
                        r.<String>get(c.getPropertyName()), getString(c)
                );
            }
        },
        NOT_LIKE {
            @Override
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                return b.notLike(
                        r.<String>get(c.getPropertyName()), getString(c)
                );
            }
        },
        IN {
            @Override
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                Object o = c.getCompareTo();
                if(o == null)
                    return r.get(c.getPropertyName()).in();
                if(o instanceof Collection)
                    return r.get(c.getPropertyName()).in((Collection) o);
                throw new IllegalArgumentException(c.getPropertyName());
            }
        },
        NOT_IN {
            @Override
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                Object o = c.getCompareTo();
                if(o == null)
                    return b.not(r.get(c.getPropertyName()).in());
                if(o instanceof Collection)
                    return b.not(r.get(c.getPropertyName()).in((Collection) o));
                throw new IllegalArgumentException(c.getPropertyName());
            }
        },
        NULL {
            @Override
            public Predicate toPredicate(Criterion c, Root<?>r, CriteriaBuilder b)
            {
                return r.get(c.getPropertyName()).isNull();
            }
        },
        NOT_NULL {
            @Override
            public Predicate toPredicate(Criterion c, Root<?> r, CriteriaBuilder b) {
                return r.get(c.getPropertyName()).isNotNull();
            }
        },
        OR {
            @Override
            public Predicate toPredicate(Criterion c, Root<?> r, CriteriaBuilder b) {
                return b.or(c.getArg1Criterion().getOperator().toPredicate(c.getArg1Criterion(), r, b),
                        c.getArg2Criterion().getOperator().toPredicate(c.getArg2Criterion(), r, b));
            }
        },
        AND {
            @Override
            public Predicate toPredicate(Criterion c, Root<?> r, CriteriaBuilder b) {
                return b.and(c.getArg1Criterion().getOperator().toPredicate(c.getArg1Criterion(), r, b),
                        c.getArg2Criterion().getOperator().toPredicate(c.getArg2Criterion(), r, b));
            }
        }
        ;



        public abstract Predicate toPredicate(Criterion c, Root<?> r,
                                              CriteriaBuilder b);

        @SuppressWarnings("unchecked")
        private static Comparable<?> getComparable(Criterion c)
        {
            Object o = c.getCompareTo();
            if(o != null && !(o instanceof Comparable))
                throw new IllegalArgumentException(c.getPropertyName());
            return (Comparable<?>)o;
        }

        private static String getString(Criterion c)
        {
            if(!(c.getCompareTo() instanceof String))
                throw new IllegalArgumentException(c.getPropertyName());
            return (String)c.getCompareTo();
        }
    }
}
