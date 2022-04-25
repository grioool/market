export const isBlank: (obj: unknown) => boolean =
    (obj: unknown): boolean => obj === undefined || obj === null;

export const isPresent: (obj: unknown) => boolean =
    (obj: unknown): boolean => obj !== undefined && obj !== null;
