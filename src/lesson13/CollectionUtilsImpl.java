package lesson13;

import java.util.*;

public class CollectionUtilsImpl implements CollectionUtils {
    @Override
    public Collection<Integer> union(Collection<Integer> a, Collection<Integer> b) throws NullPointerException {
        List<Integer> result = new ArrayList<>();
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    @Override
    public Collection<Integer> intersection(Collection<Integer> a, Collection<Integer> b) throws NullPointerException {
        //Через Set'ы убираем повторные сравнения с дубликатами во входящих коллекциях
        Set<Integer> inA = new HashSet<>(a);
        Set<Integer> inB = new HashSet<>(b);
        List<Integer> result = new ArrayList<>();
        for (Integer element : a) {
            if (inB.contains(element)) {
                result.add(element);
            }
        }

        for (Integer element : b) {
            if (inA.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public Set<Integer> unionWithoutDuplicate(Collection<Integer> a, Collection<Integer> b) throws NullPointerException {
        Set<Integer> result = new HashSet<>();
        result.addAll(a);
        result.addAll(b);
        return result;
    }

    @Override
    public Set<Integer> intersectionWithoutDuplicate(Collection<Integer> a, Collection<Integer> b) throws NullPointerException {
        //Через Set'ы убираем повторные сравнения с дубликатами во входящих коллекциях
        Set<Integer> inA = new HashSet<>(a);
        Set<Integer> inB = new HashSet<>(b);
        Set<Integer> result = new HashSet<>();
        for (Integer element : a) {
            if (inB.contains(element)) {
                result.add(element);
            }
        }

        for (Integer element : b) {
            if (inA.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    @Override
    public Collection<Integer> difference(Collection<Integer> a, Collection<Integer> b) throws NullPointerException {
        //Через Set'ы убираем повторные сравнения с дубликатами во входящих коллекциях
        Set<Integer> inA = new HashSet<>(a);
        Set<Integer> inB = new HashSet<>(b);
        List<Integer> result = new ArrayList<>();
        for (Integer element : a) {
            if (!inB.contains(element)) {
                result.add(element);
            }
        }

        for (Integer element : b) {
            if (!inA.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }
}