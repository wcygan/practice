#[cfg(test)]
mod tests {
    use std::sync::{Arc, Mutex};

    #[test]
    fn mutex_counter() {
        let num_threads = 10;
        let counter = Arc::new(Mutex::new(1));
        let mut threads = vec![];

        for _ in 1..num_threads {
            let ctr = Arc::clone(&counter);
            let t = std::thread::spawn(move || {
                let mut count = ctr.lock().unwrap();
                *count += 1;
            });
            threads.push(t)
        }

        for t in threads {
            t.join().unwrap();
        }

        {
            let count = *counter.lock().unwrap();
            assert_eq!(num_threads, count)
        }
    }
}